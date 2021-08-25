package com.aperezsi.core.usecase

abstract class UseCase<PARAMS, RETURN> {

    abstract suspend operator fun invoke(params: PARAMS? = null): RETURN
}