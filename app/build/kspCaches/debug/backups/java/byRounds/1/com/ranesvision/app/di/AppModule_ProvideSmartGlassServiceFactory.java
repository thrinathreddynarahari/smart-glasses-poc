package com.ranesvision.app.di;

import com.ranesvision.app.data.sdk.HeyCyanManager;
import com.ranesvision.app.domain.service.SmartGlassService;
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
public final class AppModule_ProvideSmartGlassServiceFactory implements Factory<SmartGlassService> {
  private final Provider<HeyCyanManager> managerProvider;

  public AppModule_ProvideSmartGlassServiceFactory(Provider<HeyCyanManager> managerProvider) {
    this.managerProvider = managerProvider;
  }

  @Override
  public SmartGlassService get() {
    return provideSmartGlassService(managerProvider.get());
  }

  public static AppModule_ProvideSmartGlassServiceFactory create(
      Provider<HeyCyanManager> managerProvider) {
    return new AppModule_ProvideSmartGlassServiceFactory(managerProvider);
  }

  public static SmartGlassService provideSmartGlassService(HeyCyanManager manager) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideSmartGlassService(manager));
  }
}
