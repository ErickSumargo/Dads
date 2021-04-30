rootProject.name = "Dads"

SharedProject(settings).also { project ->
    project.importModules()
}

DataProject(settings).also { project ->
    project.importModules()
}

DomainProject(settings).also { project ->
    project.importModules()
}

AndroidPlatformProject(settings).also { project ->
    project.importModules()
}

class SharedProject(settings: Settings) : Settings by settings {

    fun importModules() {
        include(":shared")
    }
}

class DataProject(settings: Settings) : Settings by settings {

    fun importModules() {
        include(":data:database")
        include(":data:database_test")
        include(":data:remote")
        include(":data:remote_test")
    }
}

class DomainProject(settings: Settings) : Settings by settings {

    fun importModules() {
        include(":domain:home")
    }
}

class AndroidPlatformProject(settings: Settings) : Settings by settings {

    fun importModules() {
        importAppModules()
        importInternalModules()
        importFeatureModules()
        importLibraryModules()
    }

    private fun importAppModules() {
        include(":android:app")
    }

    private fun importInternalModules() {
        include(":android:annotation")
        include(":android:processor")
    }

    private fun importFeatureModules() {
        include(":android:feature:home")
    }

    private fun importLibraryModules() {
        include(":android:library:instrumentation")
        include(":android:library:preference")
        include(":android:library:preference_test")
        include(":android:library:presentation")
        include(":android:library:threading")
        include(":android:library:threading_test")
        include(":android:library:worker")
    }
}
