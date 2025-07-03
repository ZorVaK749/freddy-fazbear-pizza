const API_URL = "http://localhost:8080"; // Asegúrate que sea el puerto correcto del backend

export async function getProductos() {
  try {
    const response = await fetch(`${API_URL}/productos`);
    if (!response.ok) {
      throw new Error("No se pudieron obtener los productos");
    }
    const data = await response.json();
    console.log("✅ Productos recibidos:", data);
    return data;
  } catch (error) {
    console.error("❌ Error al obtener productos:", error);
    return [];
  }
}

export async function agregarProducto(producto) {
  try {
    const response = await fetch(`${API_URL}/productos`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(producto),
    });

    if (!response.ok) {
      throw new Error("Error al agregar producto");
    }

    const data = await response.json();
    console.log("✅ Producto agregado:", data);
    return data;
  } catch (error) {
    console.error("❌ Error al agregar producto:", error);
    return null;
  }
}
