// map.js
// Inicialización del mapa de locales con Leaflet.js - ID corregido

document.addEventListener("DOMContentLoaded", () => {
  const mapContainer = document.getElementById("map"); // Corregido: era "mapid"
  if (!mapContainer) return;

  // Inicializar mapa centrado en Estados Unidos
  const map = L.map("map").setView([39.8283, -98.5795], 4);

  // Agregar capa de tiles
  L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
    attribution: "&copy; <a href='https://www.openstreetmap.org/copyright'>OpenStreetMap</a> contributors"
  }).addTo(map);

  // Locales de Freddy Fazbear's Pizza (datos mock)
  const locales = [
    { 
      name: "Freddy's Hurricane", 
      coords: [37.1853, -111.6457], 
      address: "1987 Pizzaplex Avenue, Hurricane, Utah",
      phone: "(435) 555-FRED"
    },
    { 
      name: "Freddy's New York", 
      coords: [40.7128, -74.0060], 
      address: "123 Broadway, New York, NY",
      phone: "(212) 555-FRED"
    },
    { 
      name: "Freddy's Los Angeles", 
      coords: [34.0522, -118.2437], 
      address: "456 Hollywood Blvd, Los Angeles, CA",
      phone: "(323) 555-FRED"
    },
    { 
      name: "Freddy's Chicago", 
      coords: [41.8781, -87.6298], 
      address: "789 Michigan Ave, Chicago, IL",
      phone: "(312) 555-FRED"
    },
    { 
      name: "Freddy's Miami", 
      coords: [25.7617, -80.1918], 
      address: "321 Ocean Drive, Miami, FL",
      phone: "(305) 555-FRED"
    }
  ];

  // Crear marcadores para cada local
  locales.forEach(local => {
    const marker = L.marker(local.coords).addTo(map);
    
    // Popup con información del local
    const popupContent = `
      <div style="font-family: 'Roboto', sans-serif; min-width: 200px;">
        <h3 style="color: #facc15; font-weight: bold; margin-bottom: 8px;">${local.name}</h3>
        <p style="margin: 4px 0; color: #333;"><strong>📍 Dirección:</strong><br>${local.address}</p>
        <p style="margin: 4px 0; color: #333;"><strong>📞 Teléfono:</strong><br>${local.phone}</p>
        <p style="margin: 8px 0; color: #666; font-size: 12px;">
          <em>"¡Donde la fantasía y la diversión cobran vida!"</em>
        </p>
      </div>
    `;
    
    marker.bindPopup(popupContent);
  });

  // Opcional: Función para cargar locales desde API
  async function loadLocationsFromAPI() {
    try {
      const response = await fetch('/api/locations');
      if (response.ok) {
        const locations = await response.json();
        
        // Limpiar marcadores existentes si es necesario
        // map.eachLayer(layer => { if (layer instanceof L.Marker) map.removeLayer(layer); });
        
        // Agregar marcadores desde la API
        locations.forEach(location => {
          const marker = L.marker([location.latitude, location.longitude]).addTo(map);
          marker.bindPopup(`
            <div style="font-family: 'Roboto', sans-serif;">
              <h3 style="color: #facc15;">${location.name}</h3>
              <p>${location.address || 'Dirección no disponible'}</p>
            </div>
          `);
        });
      }
    } catch (error) {
      console.log('Usando datos mock - API no disponible:', error);
    }
  }

  // Intentar cargar desde API (fallback a datos mock si falla)
  loadLocationsFromAPI();
});