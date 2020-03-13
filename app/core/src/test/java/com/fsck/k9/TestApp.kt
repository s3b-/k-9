package com.fsck.k9

import android.app.Application
import com.fsck.k9.crypto.EncryptionExtractor
import com.fsck.k9.preferences.InMemoryStoragePersister
import com.fsck.k9.preferences.StoragePersister
import com.fsck.k9.storage.storageModule
import com.nhaarman.mockitokotlin2.mock
import org.koin.dsl.module

class TestApp : Application() {
    override fun onCreate() {
        Core.earlyInit(this)

        super.onCreate()
        DI.start(this, coreModules + storageModule + testModule)

        K9.init(this)
        Core.init(this)
    }
}

val testModule = module {
    single { AppConfig(emptyList()) }
    single { mock<CoreResourceProvider>() }
    single { mock<EncryptionExtractor>() }
    single<StoragePersister> { InMemoryStoragePersister() }
}
