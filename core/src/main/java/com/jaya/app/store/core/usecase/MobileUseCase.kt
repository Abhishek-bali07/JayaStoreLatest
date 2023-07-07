package com.jaya.app.store.core.usecase


import com.jaya.app.store.core.common.constants.Data
import com.jaya.app.store.core.common.constants.Destination
import com.jaya.app.store.core.common.constants.Resource
import com.jaya.app.store.core.common.enums.EmitType
import com.jaya.app.store.core.domain.repositories.MobileRepository
import com.jaya.app.store.core.utils.handleFailedResponse
import com.jaya.app.store.core.utils.helper.AppStore
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MobileUseCase @Inject constructor(
    private val repository: MobileRepository,
    private val pref: AppStore

) {

    fun sendOtp(mobileNumber: String) = flow {
        emit(Data(EmitType.Loading, true))
        when(val response = repository.login(mobileNumber)){
            is Resource.Success ->{
                emit(Data(EmitType.Loading, false))
                response.data?.apply {
                    when(status){
                        true ->{
                            emit(Data(type = EmitType.Inform, isSend))
                           // emit(Data(type = EmitType.Navigate, Destination.OtpScreen))
                        }

                        else -> {
                            emit(Data(EmitType.BackendError, message))
                        }
                    }
                }
            }
            is Resource.Error ->{
                emit(Data(EmitType.Loading, false))
                handleFailedResponse(
                    response = response,
                    message = response.message,
                    emitType = EmitType.NetworkError
                )
            }

            else -> {}
        }
    }



    fun verify(mobileNumber: String, otp: String) = flow {
        emit(Data(type = EmitType.Loading, true))
        when(val response = repository.verify(mobileNumber, otp)){
            is Resource.Success ->{
                emit(Data(type = EmitType.Loading, false))
                response.data?.apply {
                    when (status) {
                        true -> {
                            if (isVerified) {
                                pref.login(userId)
                                emit(Data(type = EmitType.Navigate, Destination.DashBoardScreen))
                            } else {
                                emit(Data(type = EmitType.BackendError, message))
                            }
                        }
                        else -> {
                            emit(Data(type = EmitType.NetworkError, message))
                        }
                    }
                }
            }
            is Resource.Error -> {
                emit(Data(type = EmitType.Loading, false))
                handleFailedResponse(
                    response = response,
                    message = response.message,
                    emitType = EmitType.NetworkError
                )
            }

            else -> {}
        }
    }
}