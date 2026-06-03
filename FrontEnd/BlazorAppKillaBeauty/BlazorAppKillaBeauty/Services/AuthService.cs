public class AuthService
{
    public bool EstaLogueado { get; private set; }
    public string NombreUsuario { get; private set; } = "";
    public string Rol { get; private set; } = "";

    public void LoginCliente(string nombre)
    {
        EstaLogueado = true;
        NombreUsuario = nombre;
        Rol = "Cliente";
    }

    public void LoginAdmin(string nombre)
    {
        EstaLogueado = true;
        NombreUsuario = nombre;
        Rol = "Administrador";
    }

    public void LoginSuperAdmin(string nombre)
    {
        EstaLogueado = true;
        NombreUsuario = nombre;
        Rol = "SuperAdmin";
    }

    public void Logout()
    {
        EstaLogueado = false;
        NombreUsuario = "";
        Rol = "";
    }
}