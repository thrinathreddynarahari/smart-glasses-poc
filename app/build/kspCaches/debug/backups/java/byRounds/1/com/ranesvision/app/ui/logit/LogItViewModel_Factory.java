package com.ranesvision.app.ui.logit;

import android.content.Context;
import com.ranesvision.app.data.repository.LogItRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class LogItViewModel_Factory implements Factory<LogItViewModel> {
  private final Provider<LogItRepository> repositoryProvider;

  private final Provider<Context> contextProvider;

  public LogItViewModel_Factory(Provider<LogItRepository> repositoryProvider,
      Provider<Context> contextProvider) {
    this.repositoryProvider = repositoryProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public LogItViewModel get() {
    return newInstance(repositoryProvider.get(), contextProvider.get());
  }

  public static LogItViewModel_Factory create(Provider<LogItRepository> repositoryProvider,
      Provider<Context> contextProvider) {
    return new LogItViewModel_Factory(repositoryProvider, contextProvider);
  }

  public static LogItViewModel newInstance(LogItRepository repository, Context context) {
    return new LogItViewModel(repository, context);
  }
}
