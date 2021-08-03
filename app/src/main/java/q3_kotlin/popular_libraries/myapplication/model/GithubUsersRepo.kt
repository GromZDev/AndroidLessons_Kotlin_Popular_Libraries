package q3_kotlin.popular_libraries.myapplication.model

class GithubUsersRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5"),
        GithubUser("login6"),
        GithubUser("login7"),
        GithubUser("login8"),
        GithubUser("login9"),
        GithubUser("login10"),
        GithubUser("login11"),
        GithubUser("login12"),
        GithubUser("login13"),
        GithubUser("login14"),
        GithubUser("login15"),
        GithubUser("login16")
    )

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}
