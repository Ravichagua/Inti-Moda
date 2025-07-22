document.addEventListener("DOMContentLoaded", function () {
  const token = localStorage.getItem("jwt");

  if (token) {
    try {
      const payload = JSON.parse(atob(token.split('.')[1]));
      const usuario = payload.sub;
      const nombreElemento = document.getElementById("nombreUsuario");
      const contenedor = document.getElementById("bienvenida");

      if (nombreElemento && contenedor) {
        nombreElemento.textContent = usuario;
        contenedor.style.display = "block";
      }
    } catch (e) {
      console.error("Token inválido", e);
    }
  }
});
