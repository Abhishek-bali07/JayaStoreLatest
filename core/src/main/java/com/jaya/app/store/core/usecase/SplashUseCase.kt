package com.jaya.app.store.core.usecase


import com.jaya.app.store.core.common.constants.Data
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.common.enums.IntroStatus
import com.jaya.app.store.core.domain.repositories.SplashRepository
import com.jaya.app.store.core.utils.handleFailedResponse
import com.jaya.app.store.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SplashUseCase @Inject constructor(
    private  val splashRepository: SplashRepository,
    private  val appStore: AppStore
) {
    fun checkIntroStatus() = flow {
        if (!appStore.isIntroDone()){
            appStore.intro(true)
            emit(Data(type = EmitType.IntroStatus, IntroStatus.NOT_DONE))
        }else{
            emit(Data(type = EmitType.IntroStatus, IntroStatus.DONE))
        }
    }

    fun checkAppVersion() = flow {
        when(val response  = splashRepository.appVersion(1)){
            is Resource.Success -> {
                response.data?.apply {
                    when (status) {
                        true -> {
                            if (1 < appVersion.versionCode) {
                                emit(Data(type = EmitType.AppVersion, value = appVersion))
                            } else {
                                if(appStore.isLoggedIn()) {
                                    emit(
                                        Data(
                                            type = EmitType.Navigate,
                                           value = Destination.DashBoardScreen
                                        )
                                    )
                                } else {
                                    emit(
                                        Data(
                                            type = EmitType.Navigate,
                                            value = Destination.MobileNumberScreen
                                        )
                                    )
                                }
                            }
                        }
                        else -> {
                            emit(Data(type = EmitType.BackendError, value = message))
                        }
                    }
                }
            }
            is Resource.Error -> {
                handleFailedResponse(
                    response = response,
                    message = response.message,
                    emitType = EmitType.NetworkError
                )
            }
            else -> {}
        }
    }

    fun  navigateToAppropiateScreen() = flow<Data> {
        if (appStore.isLoggedIn()){
            emit(Data(type = EmitType.Navigate, value = Destination.MobileNumberScreen))
        }else{
            emit(Data(type = EmitType.Navigate, value = Destination.SplashScreen))
        }
    }


}