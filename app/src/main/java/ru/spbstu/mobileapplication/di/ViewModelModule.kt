package ru.spbstu.mobileapplication.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.RestoreAccessViewModel
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.SignInViewModel
import ru.spbstu.mobileapplication.presentation.authorization_activity.view_models.SignUpViewModel
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.CabinetViewModel
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.ChatViewModel
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.CompilationViewModel
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.FavoriteViewModel
import ru.spbstu.mobileapplication.presentation.bottom_navigation.view_models.HomeViewModel
import ru.spbstu.mobileapplication.presentation.interview.view_models.ApartmentTypeViewModel
import ru.spbstu.mobileapplication.presentation.interview.view_models.AreaViewModel
import ru.spbstu.mobileapplication.presentation.interview.view_models.BudgetViewModel
import ru.spbstu.mobileapplication.presentation.interview.view_models.CityViewModel
import ru.spbstu.mobileapplication.presentation.interview.view_models.PurposeViewModel
import ru.spbstu.mobileapplication.presentation.interview.view_models.TermViewModel

@Module
@InstallIn(SingletonComponent::class)
interface ViewModelModule {

    // Authorization
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

    // Bottom navigation
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CompilationViewModel::class)
    fun bindCompilationViewModel(viewModel: CompilationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    fun bindChatViewModel(viewModel: ChatViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CabinetViewModel::class)
    fun bindCabinetViewModel(viewModel: CabinetViewModel): ViewModel

    // Interview
    @Binds
    @IntoMap
    @ViewModelKey(PurposeViewModel::class)
    fun bindPurposeViewModel(viewModel: PurposeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TermViewModel::class)
    fun bindTermViewModel(viewModel: TermViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ApartmentTypeViewModel::class)
    fun bindApartmentTypeViewModel(viewModel: ApartmentTypeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AreaViewModel::class)
    fun bindAreaViewModel(viewModel: AreaViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BudgetViewModel::class)
    fun bindBudgetViewModel(viewModel: BudgetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CityViewModel::class)
    fun bindCityViewModel(viewModel: CityViewModel): ViewModel
}
