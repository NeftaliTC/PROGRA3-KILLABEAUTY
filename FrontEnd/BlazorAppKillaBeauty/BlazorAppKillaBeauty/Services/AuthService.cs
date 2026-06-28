using System.Net;

public class AuthService
{
    public bool EstaLogueado { get; private set; }
    public int? UsuarioId { get; private set; }
    public string NombreUsuario { get; private set; } = "";
    public string CorreoElectronico { get; private set; } = "";
    public string Rol { get; private set; } = "";

    public string Dni { get; private set; } = "";

    public void Login(string nombre, int usuarioId, string rol, string correoElectronico, string dni)
    {
        EstaLogueado = true;
        UsuarioId = usuarioId;
        NombreUsuario = nombre;
        CorreoElectronico = correoElectronico;
        Dni = dni;
        Rol = rol ?? "Cliente";  // si llega null se le asigna cliente 
    }
    public void Logout()
    {
        EstaLogueado = false;
        UsuarioId = null;
        NombreUsuario = "";
        CorreoElectronico = "";
        Dni = "";
        Rol = "";
    }
}
