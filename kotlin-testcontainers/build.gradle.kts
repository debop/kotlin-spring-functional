dependencies {

    api(Libraries.testcontainers)
    api(Libraries.testcontainers_junit_jupiter)

    api(Libraries.junit_jupiter)
    api(Libraries.assertj_core)
    api(Libraries.mockk)

    api(Libraries.random_beans)
    api(Libraries.reflectasm)

    api(Libraries.mongo_java_driver)
    api(Libraries.lettuceCore)

    // NOTE: linux-x86_64 를 따로 추가해줘야 제대로 classifier가 지정된다. 이유는 모르겠지만, 이렇게 해야 제대로 된 jar를 참조한다
    api(Libraries.netty_transport_native_epoll + ":linux-x86_64")
    api(Libraries.netty_transport_native_kqueue + ":osx-x86_64")
}