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
public final class ConnectDeviceUseCase_Factory implements Factory<ConnectDeviceUseCase> {
  private final Provider<SmartGlassService> serviceProvider;

  public ConnectDeviceUseCase_Factory(Provider<SmartGlassService> serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  @Override
  public ConnectDeviceUseCase get() {
    return newInstance(serviceProvider.get());
  }

  public static ConnectDeviceUseCase_Factory create(Provider<SmartGlassService> serviceProvider) {
    return new ConnectDeviceUseCase_Factory(serviceProvider);
  }

  public static ConnectDeviceUseCase newInstance(SmartGlassService service) {
    return new ConnectDeviceUseCase(service);
  }
}
