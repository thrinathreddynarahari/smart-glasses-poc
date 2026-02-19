package com.ranesvision.app.domain.usecase;

import com.ranesvision.app.domain.service.SmartGlassService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class DisconnectUseCase_Factory implements Factory<DisconnectUseCase> {
  private final Provider<SmartGlassService> serviceProvider;

  public DisconnectUseCase_Factory(Provider<SmartGlassService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public DisconnectUseCase get() {
    return newInstance(serviceProvider.get());
  }

  public static DisconnectUseCase_Factory create(Provider<SmartGlassService> serviceProvider) {
    return new DisconnectUseCase_Factory(serviceProvider);
  }

  public static DisconnectUseCase newInstance(SmartGlassService service) {
    return new DisconnectUseCase(service);
  }
}
