package ru.spbstu.mobileapplication.data.mapper

import ru.spbstu.mobileapplication.data.database.user.UserDBModel
import ru.spbstu.mobileapplication.data.network.user.model.request.UpdateUserRequest
import ru.spbstu.mobileapplication.data.network.user.model.response.GetUserResponse
import ru.spbstu.mobileapplication.domain.enums.Gender
import ru.spbstu.mobileapplication.domain.user.entity.EditUserItem
import ru.spbstu.mobileapplication.domain.user.entity.UserItem
import javax.inject.Inject

//todo: перепроверить  всё
class UserMapper @Inject constructor() {
    fun mapUserItemToUserDBModel(userItem: UserItem): UserDBModel {
        return UserDBModel(
            firstname = userItem.firstname,
            lastname = userItem.lastname,
            aboutMe = userItem.aboutMe,
            gender = Gender.valueOf(userItem.gender ?: ""),
            birthdayDate = userItem.birthdayDate,
            phone = userItem.phone,
            email = userItem.email,
            photos = userItem.photos
        )
    }

    fun mapUserDBModelToUserItem(userDBModel: UserDBModel): UserItem {
        return UserItem(
            email = userDBModel.email,
            firstname = userDBModel.firstname,
            lastname = userDBModel.lastname,
            aboutMe = userDBModel.aboutMe,
            gender = userDBModel.gender?.name ?: "",
            birthdayDate = userDBModel.birthdayDate,
            phone = userDBModel.phone,
            photos = userDBModel.photos
        )
    }

    fun mapEditUserItemToUserUpdateRequest(editUserItem: EditUserItem): UpdateUserRequest {
        return UpdateUserRequest(
            firstName = editUserItem.firstname,
            lastName = editUserItem.lastname,
            about = editUserItem.about,
            gender = editUserItem.gender,
            birthdayDate = editUserItem.birthdayDate,
            phone = editUserItem.phone
        )
    }


    fun mapUserUpdateRequestToUserItem(getUserResponse: GetUserResponse): UserItem {
        return UserItem(
            email = getUserResponse.email ?: "",
            firstname = getUserResponse.firstname ?: "",
            lastname = getUserResponse.lastname,
            aboutMe = getUserResponse.about,
            gender = getUserResponse.gender?.name ?: "",
            birthdayDate = getUserResponse.birthdayDate,
            phone = getUserResponse.phone ?: "",
            photos = getUserResponse.photos
        )
    }
}