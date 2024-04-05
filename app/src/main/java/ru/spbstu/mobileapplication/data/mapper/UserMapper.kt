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
        val genderValue = userItem.gender?.let { gender ->
            runCatching { Gender.valueOf(gender) }.getOrNull() ?: Gender.UNKNOWN
        } ?: Gender.UNKNOWN

        return UserDBModel(
            userId = userItem.userId,
            firstname = userItem.firstname,
            lastname = userItem.lastname,
            about = userItem.about,
            gender = genderValue,
            birthdayDate = userItem.birthdayDate,
            phone = userItem.phone,
            email = userItem.email,
            photos = userItem.photos
        )
    }

    fun mapUserDBModelToUserItem(userDBModel: UserDBModel) = UserItem(
        userId = userDBModel.userId,
        email = userDBModel.email,
        firstname = userDBModel.firstname,
        lastname = userDBModel.lastname,
        about = userDBModel.about,
        gender = userDBModel.gender?.name ?: "",
        birthdayDate = userDBModel.birthdayDate,
        phone = userDBModel.phone,
        photos = userDBModel.photos
    )

    fun mapEditUserItemToUserUpdateRequest(editUserItem: EditUserItem) = UpdateUserRequest(
        firstName = editUserItem.firstname,
        lastName = editUserItem.lastname,
        about = editUserItem.about,
        gender = editUserItem.gender,
        birthdayDate = editUserItem.birthdayDate,
        phone = editUserItem.phone
    )


    fun mapUserUpdateRequestToUserItem(getUserResponse: GetUserResponse) = UserItem(
        userId = getUserResponse.userId,
        email = getUserResponse.email ?: "",
        firstname = getUserResponse.firstname ?: "",
        lastname = getUserResponse.lastname,
        about = getUserResponse.about,
        gender = getUserResponse.gender?.name ?: "",
        birthdayDate = getUserResponse.birthdayDate,
        phone = getUserResponse.phone ?: "",
        photos = getUserResponse.photos
    )

}