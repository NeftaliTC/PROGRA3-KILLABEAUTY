public class AuthService
{
    public bool EstaLogueado { get; private set; }
    public int? UsuarioId { get; private set; }
    public string NombreUsuario { get; private set; } = "";
    public string Rol { get; private set; } = "";

    public void LoginCliente(string nombre, int usuarioId = 2)
    {
        EstaLogueado = true;
        UsuarioId = usuarioId;
        NombreUsuario = nombre;
        Rol = "Cliente";
    }

    public void LoginAdmin(string nombre, int usuarioId = 1)
    {
        EstaLogueado = true;
        UsuarioId = usuarioId;
        NombreUsuario = nombre;
        Rol = "Administrador";
    }

    public void LoginSuperAdmin(string nombre, int usuarioId = 1)
    {
        EstaLogueado = true;
        UsuarioId = usuarioId;
        NombreUsuario = nombre;
        Rol = "SuperAdmin";
    }

    public void Logout()
    {
        EstaLogueado = false;
        UsuarioId = null;
        NombreUsuario = "";
        Rol = "";
    }
}
