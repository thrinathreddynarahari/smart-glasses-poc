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
public final class DeviceConnector_Factory implements Factory<DeviceConnector> {
  private final Provider<Context> contextProvider;

  public DeviceConnector_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DeviceConnector get() {
    return newInstance(contextProvider.get());
  }

  public static DeviceConnector_Factory create(Provider<Context> contextProvider) {
    return new DeviceConnector_Factory(contextProvider);
  }

  public static DeviceConnector newInstance(Context context) {
    return new DeviceConnector(context);
  }
}
