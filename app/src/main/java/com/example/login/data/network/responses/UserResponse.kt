package com.example.login.data.network.responses

data class UserResponse(val isSuccessful : Boolean,
                        val id : Int?,
                        val username:String?,
                        val email: String?,
                        val roles : List<String?>?,
                        val tokenType: String?,
                        val accessToken: String?
)
