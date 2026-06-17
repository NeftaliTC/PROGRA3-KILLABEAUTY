using BlazorAppKillaBeauty.Components;
using BlazorAppKillaBeauty.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorComponents()
    .AddInteractiveServerComponents();

// SERVICE
builder.Services.AddSingleton<AuthService>();
builder.Services.AddSingleton<CartService>();
builder.Services.AddScoped<CuponService>();
builder.Services.AddScoped<CampanaService>();
builder.Services.AddScoped<MarcaService>();
builder.Services.AddHttpClient("KillaApi", client =>
{
    var baseUrl = builder.Configuration["KillaApi:BaseUrl"]
        ?? "http://localhost:8080/KillaREST-1.0-SNAPSHOT/services/";

    client.BaseAddress = new Uri(baseUrl);
    client.Timeout = TimeSpan.FromSeconds(30);
});



builder.Services.AddSingleton<AddressService>();
builder.Services.AddScoped<ProductoService>();
var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error", createScopeForErrors: true);
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}
app.UseStatusCodePagesWithReExecute("/not-found", createScopeForStatusCodePages: true);
app.UseHttpsRedirection();

app.UseAntiforgery();

app.MapStaticAssets();
app.MapRazorComponents<App>()
    .AddInteractiveServerRenderMode();

app.Run();
