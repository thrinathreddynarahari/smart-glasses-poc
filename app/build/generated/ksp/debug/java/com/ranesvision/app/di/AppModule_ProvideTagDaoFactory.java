package com.ranesvision.app.di;

import com.ranesvision.app.data.local.AppDatabase;
import com.ranesvision.app.data.local.dao.TagDao;
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
public final class AppModule_ProvideTagDaoFactory implements Factory<TagDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideTagDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TagDao get() {
    return provideTagDao(databaseProvider.get());
  }

  public static AppModule_ProvideTagDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideTagDaoFactory(databaseProvider);
  }

  public static TagDao provideTagDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideTagDao(database));
  }
}
