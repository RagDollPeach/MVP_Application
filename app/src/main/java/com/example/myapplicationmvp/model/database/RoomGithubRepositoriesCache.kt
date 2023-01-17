package com.example.myapplicationmvp.model.database


import com.example.myapplicationmvp.core.mapper.UserMapper
import com.example.myapplicationmvp.core.networck.UsersApi
import com.example.myapplicationmvp.core.utils.doCompletableIf
import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.data.Repo
import io.reactivex.rxjava3.core.Single

class RoomGithubRepositoriesCache : RoomRepos {

    override fun getRepos(shouldPersist: Boolean, repos: ReposDAO,usersApi: UsersApi,user: GithubUser): Single<List<Repo>> {
        return usersApi.getAllRepos().doCompletableIf(shouldPersist) { repo ->
            repos.insertAllRepos(repo.map { UserMapper.mapToRepoEntity(it,user) })
        }
    }

    override fun getReposFromDb(repos: ReposDAO,userId: String): Single<List<Repo>> {
        return repos.queryForAllRepos(userId).map { it.map(UserMapper::mapToRepo) }
    }
}