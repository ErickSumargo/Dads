include(":app")
rootProject.name = "Dads"

val features: List<String> = listOf(
    "feature_home"
)

val domains: List<String> = listOf(
    "domain_common",
    "domain_home"
)

val libs: List<String> = listOf(
    "lib_database",
    "lib_database_test",
    "lib_instrumentation",
    "lib_preference",
    "lib_preference_test",
    "lib_presentation",
    "lib_remote",
    "lib_remote_test",
    "lib_threading",
    "lib_threading_test",
    "lib_worker"
)

val internals: List<String> = listOf(
    "annotation",
    "processor"
)

features.forEach { feature ->
//    include(":$feature")
//    project(":$feature").projectDir = File("features/$feature")
}

domains.forEach { domain ->
//    include(":$domain")
//    project(":$domain").projectDir = File("domains/$domain")
}

libs.forEach { lib ->
//    include(":$lib")
//    project(":$lib").projectDir = File("libs/$lib")
}

internals.forEach { internal ->
//    include(":$internal")
}
