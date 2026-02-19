package com.ranesvision.app.ui.album;

import android.content.Context;
import com.ranesvision.app.data.repository.AlbumRepository;
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
public final class AlbumViewModel_Factory implements Factory<AlbumViewModel> {
  private final Provider<AlbumRepository> repositoryProvider;

  private final Provider<Context> contextProvider;

  public AlbumViewModel_Factory(Provider<AlbumRepository> repositoryProvider,
      Provider<Context> contextProvider) {
    this.repositoryProvider = repositoryProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public AlbumViewModel get() {
    return newInstance(repositoryProvider.get(), contextProvider.get());
  }

  public static AlbumViewModel_Factory create(Provider<AlbumRepository> repositoryProvider,
      Provider<Context> contextProvider) {
    return new AlbumViewModel_Factory(repositoryProvider, contextProvider);
  }

  public static AlbumViewModel newInstance(AlbumRepository repository, Context context) {
    return new AlbumViewModel(repository, context);
  }
}
