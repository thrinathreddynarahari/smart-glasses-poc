package com.ranesvision.app.data.repository;

import com.ranesvision.app.data.local.dao.LogItDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class LogItRepository_Factory implements Factory<LogItRepository> {
  private final Provider<LogItDao> logItDaoProvider;

  public LogItRepository_Factory(Provider<LogItDao> logItDaoProvider) {
    this.logItDaoProvider = logItDaoProvider;
  }

  @Override
  public LogItRepository get() {
    return newInstance(logItDaoProvider.get());
  }

  public static LogItRepository_Factory create(Provider<LogItDao> logItDaoProvider) {
    return new LogItRepository_Factory(logItDaoProvider);
  }

  public static LogItRepository newInstance(LogItDao logItDao) {
    return new LogItRepository(logItDao);
  }
}
