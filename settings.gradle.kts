rootProject.name = "Dads"

val domains = listOf(
    "domain_common",
    "domain_home"
)

val features = listOf(
    "feature_home"
)

val libs = listOf(
    "lib_database",
    "lib_database_test",
    "lib_instrumentation",
    "lib_navigation",
    "lib_preference",
    "lib_preference_test",
    "lib_presentation",
    "lib_remote",
    "lib_remote_test",
    "lib_threading",
    "lib_threading_test",
    "lib_worker"
)

val internals = listOf(
    "annotation",
    "processor"
)

include(":app")

domains.forEach { domain ->
    include(":$domain")
    project(":$domain").projectDir = File("domains/$domain")
}

features.forEach { feature ->
    include(":$feature")
    project(":$feature").projectDir = File("features/$feature")
}

libs.forEach { lib ->
    include(":$lib")
    project(":$lib").projectDir = File("libs/$lib")
}

internals.forEach { internal ->
    include(":$internal")
}
