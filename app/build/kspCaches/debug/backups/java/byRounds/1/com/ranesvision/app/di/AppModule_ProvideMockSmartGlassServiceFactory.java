package com.ranesvision.app.di;

import com.ranesvision.app.data.sdk.MockSmartGlassService;
import com.ranesvision.app.domain.service.SmartGlassService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("javax.inject.Named")
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
public final class AppModule_ProvideMockSmartGlassServiceFactory implements Factory<SmartGlassService> {
  private final Provider<MockSmartGlassService> mockServiceProvider;

  public AppModule_ProvideMockSmartGlassServiceFactory(
      Provider<MockSmartGlassService> mockServiceProvider) {
    this.mockServiceProvider = mockServiceProvider;
  }

  @Override
  public SmartGlassService get() {
    return provideMockSmartGlassService(mockServiceProvider.get());
  }

  public static AppModule_ProvideMockSmartGlassServiceFactory create(
      Provider<MockSmartGlassService> mockServiceProvider) {
    return new AppModule_ProvideMockSmartGlassServiceFactory(mockServiceProvider);
  }

  public static SmartGlassService provideMockSmartGlassService(MockSmartGlassService mockService) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideMockSmartGlassService(mockService));
  }
}
