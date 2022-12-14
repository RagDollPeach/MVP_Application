package com.example.myapplicationmvp.model.reposytories.impl

import com.example.myapplicationmvp.model.data.GithubUser
import com.example.myapplicationmvp.model.reposytories.GithubRepository
import io.reactivex.rxjava3.core.Single

class GithubRepositoryImpl: GithubRepository {

    private val repositories = listOf(
        GithubUser("MrFoxy","Anna",25),
        GithubUser("hater", "Pedro",18),
        GithubUser("Wednesday","Jenna",20),
        GithubUser("Alzheimer","Parkenson",85),
        GithubUser("SaltajBolTay","Valera",17),
        GithubUser("JAzeMat","Vera",37),
        GithubUser("Vini_S_Puhoj","Nika",23),
        GithubUser("Salt","Angelina",55)
    )

    override fun getUsers(): Single<List<GithubUser>> {
        return Single.create { it.onSuccess(repositories) }
    }
}