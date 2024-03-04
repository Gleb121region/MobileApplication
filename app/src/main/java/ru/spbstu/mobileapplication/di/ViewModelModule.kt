package ru.spbstu.mobileapplication.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.RestoreAccessViewModel
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.SignInViewModel
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.SignUpViewModel

@Module
@InstallIn(SingletonComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RestoreAccessViewModel::class)
    fun bindRestoreAccessViewModel(viewModel: RestoreAccessViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignInViewModel::class)
    fun bindSignInViewModel(viewModel: SignInViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    fun bindSignUpViewModel(viewModel: SignUpViewModel): ViewModel
}
