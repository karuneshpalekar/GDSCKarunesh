package com.karunesh.gdsckarunesh.di

import com.google.firebase.firestore.FirebaseFirestore
import com.karunesh.gdsckarunesh.business.shopDetails.ShopDetailRepository
import com.karunesh.gdsckarunesh.business.shopDetails.ShopDetailRepositoryImpl
import com.karunesh.gdsckarunesh.business.slot.SlotsRepository
import com.karunesh.gdsckarunesh.business.slot.SlotsRepositoryImpl
import com.karunesh.gdsckarunesh.business.user.UserDetailRepository
import com.karunesh.gdsckarunesh.business.user.UserDetailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFireStoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()


    @Singleton
    @Provides
    fun provideRepository(db: FirebaseFirestore): SlotsRepository = SlotsRepositoryImpl(db)

    @Singleton
    @Provides
    fun provideShopRepo(db: FirebaseFirestore):ShopDetailRepository = ShopDetailRepositoryImpl(db)


    @Singleton
    @Provides
    fun provideUserRepo(db: FirebaseFirestore):UserDetailRepository = UserDetailRepositoryImpl(db)
}