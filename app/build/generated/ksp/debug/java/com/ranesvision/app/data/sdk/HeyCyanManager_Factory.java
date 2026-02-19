package com.ranesvision.app.data.sdk;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class HeyCyanManager_Factory implements Factory<HeyCyanManager> {
  private final Provider<Context> contextProvider;

  private final Provider<DeviceScanner> deviceScannerProvider;

  private final Provider<DeviceConnector> deviceConnectorProvider;

  public HeyCyanManager_Factory(Provider<Context> contextProvider,
      Provider<DeviceScanner> deviceScannerProvider,
      Provider<DeviceConnector> deviceConnectorProvider) {
    this.contextProvider = contextProvider;
    this.deviceScannerProvider = deviceScannerProvider;
    this.deviceConnectorProvider = deviceConnectorProvider;
  }

  @Override
  public HeyCyanManager get() {
    return newInstance(contextProvider.get(), deviceScannerProvider.get(), deviceConnectorProvider.get());
  }

  public static HeyCyanManager_Factory create(Provider<Context> contextProvider,
      Provider<DeviceScanner> deviceScannerProvider,
      Provider<DeviceConnector> deviceConnectorProvider) {
    return new HeyCyanManager_Factory(contextProvider, deviceScannerProvider, deviceConnectorProvider);
  }

  public static HeyCyanManager newInstance(Context context, DeviceScanner deviceScanner,
      DeviceConnector deviceConnector) {
    return new HeyCyanManager(context, deviceScanner, deviceConnector);
  }
}
