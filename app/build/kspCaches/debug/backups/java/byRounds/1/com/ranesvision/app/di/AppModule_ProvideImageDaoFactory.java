package com.ranesvision.app.di;

import com.ranesvision.app.data.local.AppDatabase;
import com.ranesvision.app.data.local.dao.ImageDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AppModule_ProvideImageDaoFactory implements Factory<ImageDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideImageDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ImageDao get() {
    return provideImageDao(databaseProvider.get());
  }

  public static AppModule_ProvideImageDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideImageDaoFactory(databaseProvider);
  }

  public static ImageDao provideImageDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideImageDao(database));
  }
}
