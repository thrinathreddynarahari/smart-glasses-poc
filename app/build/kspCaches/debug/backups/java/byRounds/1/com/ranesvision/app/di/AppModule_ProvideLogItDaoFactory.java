package com.ranesvision.app.di;

import com.ranesvision.app.data.local.AppDatabase;
import com.ranesvision.app.data.local.dao.LogItDao;
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
public final class AppModule_ProvideLogItDaoFactory implements Factory<LogItDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideLogItDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public LogItDao get() {
    return provideLogItDao(databaseProvider.get());
  }

  public static AppModule_ProvideLogItDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideLogItDaoFactory(databaseProvider);
  }

  public static LogItDao provideLogItDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideLogItDao(database));
  }
}
