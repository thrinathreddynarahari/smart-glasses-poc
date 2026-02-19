package com.ranesvision.app.data.sdk;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class MockSmartGlassService_Factory implements Factory<MockSmartGlassService> {
  @Override
  public MockSmartGlassService get() {
    return newInstance();
  }

  public static MockSmartGlassService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MockSmartGlassService newInstance() {
    return new MockSmartGlassService();
  }

  private static final class InstanceHolder {
    private static final MockSmartGlassService_Factory INSTANCE = new MockSmartGlassService_Factory();
  }
}
