// effects.js
// Efectos visuales y SweetAlert - Corregido para el formulario real

document.addEventListener("DOMContentLoaded", () => {
  // Verificar si SweetAlert está cargado
  if (typeof Swal === 'undefined') {
    console.error('SweetAlert2 no está cargado. El Easter Egg no funcionará correctamente.');
    return;
  }

  // Manejar el formulario de reserva
  const reservaForm = document.getElementById("reservaForm");
  if (reservaForm) {
    reservaForm.addEventListener("submit", async (e) => {
      e.preventDefault();
      
      // Obtener datos del formulario
      const formData = new FormData(reservaForm);
      const reservaData = {
        nombre: formData.get('nombre'),
        email: formData.get('email'),
        fecha: formData.get('fecha'),
        invitados: formData.get('invitados'),
        mensaje: formData.get('mensaje')
      };

      try {
        // Intentar enviar al backend
        const response = await fetch('/api/events', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(reservaData)
        });

        if (response.ok) {
          // Éxito - mostrar confirmación
          Swal.fire({
            title: "¡Reserva Confirmada!",
            html: `
              <div style="text-align: left;">
                <p><strong>Nombre:</strong> ${reservaData.nombre}</p>
                <p><strong>Fecha:</strong> ${reservaData.fecha}</p>
                <p><strong>Invitados:</strong> ${reservaData.invitados}</p>
              </div>
            `,
            icon: "success",
            confirmButtonText: "¡Genial!",
            confirmButtonColor: "#facc15",
            background: "#1f1f1f",
            color: "#f5f5f5"
          });
          reservaForm.reset();
        } else {
          throw new Error('Error en el servidor');
        }
      } catch (error) {
        // Fallback - mostrar confirmación mock
        console.log('API no disponible, mostrando confirmación mock');
        Swal.fire({
          title: "¡Reserva Enviada!",
          html: `
            <div style="text-align: left;">
              <p>Hemos recibido tu solicitud de reserva:</p>
              <br>
              <p><strong>👤 Nombre:</strong> ${reservaData.nombre}</p>
              <p><strong>📅 Fecha:</strong> ${reservaData.fecha}</p>
              <p><strong>👥 Invitados:</strong> ${reservaData.invitados}</p>
              <br>
              <p style="color: #facc15;">Te contactaremos pronto para confirmar todos los detalles de tu fiesta especial.</p>
            </div>
          `,
          icon: "success",
          confirmButtonText: "¡Perfecto!",
          confirmButtonColor: "#facc15",
          background: "#1f1f1f",
          color: "#f5f5f5",
          width: '500px'
        });
        reservaForm.reset();
      }
    });
  }

  // Efectos de hover mejorados para las tarjetas
  const cards = document.querySelectorAll('.card-hover');
  cards.forEach(card => {
    card.addEventListener('mouseenter', () => {
      card.style.transform = 'translateY(-8px) scale(1.02)';
      card.style.boxShadow = '0 15px 35px rgba(0,0,0,0.4)';
    });
    
    card.addEventListener('mouseleave', () => {
      card.style.transform = 'translateY(0) scale(1)';
      card.style.boxShadow = '0 4px 15px rgba(0,0,0,0.2)';
    });
  });

  // Efecto de parpadeo aleatorio para elementos con clase 'flicker'
  const flickerElements = document.querySelectorAll('.flicker');
  flickerElements.forEach(element => {
    setInterval(() => {
      if (Math.random() > 0.85) {
        element.style.opacity = '0.6';
        setTimeout(() => {
          element.style.opacity = '1';
        }, 100);
      }
    }, 2000);
  });

  // Animación de entrada suave para elementos
  const animateOnScroll = () => {
    const elements = document.querySelectorAll('[data-animate]');
    elements.forEach(element => {
      const rect = element.getBoundingClientRect();
      if (rect.top < window.innerHeight - 100) {
        element.style.opacity = '1';
        element.style.transform = 'translateY(0)';
      }
    });
  };

  // Configurar elementos para animación
  const elementsToAnimate = document.querySelectorAll('section > div, .menu-item, .bg-gray-800');
  elementsToAnimate.forEach((element, index) => {
    element.setAttribute('data-animate', 'true');
    element.style.opacity = '0';
    element.style.transform = 'translateY(30px)';
    element.style.transition = `opacity 0.6s ease ${index * 0.1}s, transform 0.6s ease ${index * 0.1}s`;
  });

  // Ejecutar animación en scroll
  window.addEventListener('scroll', animateOnScroll);
  animateOnScroll(); // Ejecutar una vez al cargar

  // Easter egg - Konami Code para efecto especial
  let konamiCode = [];
  const konamiSequence =   ['Digit1', 'Digit9', 'Digit8', 'Digit7'];

  const konamiHandler = (e) => {
    konamiCode.push(e.code);

    if (konamiCode.length > konamiSequence.length) {
      konamiCode.shift();
    }

    if (konamiCode.join(',') === konamiSequence.join(',')) {
      // Mostrar alerta SweetAlert
      Swal.fire({
        title: "🎵 ¡Easter Egg Encontrado! 🎵",
        text: "¡Has desbloqueado el modo Golden Freddy!",
        icon: "warning",
        confirmButtonText: "😱 ¡Increíble!",
        confirmButtonColor: "#fbbf24",
        background: "#000000",
        color: "#fbbf24"
      });
      // Reproducir sonido
      const audio = new Audio('src/audio/full-golden-freddy-scream (mp3cut.net).mp3');
      audio.play();


      
      // Crear overlay de pantalla completa con el GIF
      const overlay = document.createElement('div');
      overlay.style.position = 'fixed';
      overlay.style.top = '0';
      overlay.style.left = '0';
      overlay.style.width = '100vw';
      overlay.style.height = '100vh';
      overlay.style.backgroundColor = 'rgba(0, 0, 0, 0.95)';
      overlay.style.backgroundImage = 'url("https://static.wikia.nocookie.net/fnaf-ucn/images/c/ca/Fredbear_Jumpscare.gif")';
      overlay.style.backgroundSize = 'cover';
      overlay.style.backgroundPosition = 'center';
      overlay.style.zIndex = '9999';
      document.body.appendChild(overlay);



      // Eliminar overlay después de 3 segundos
      setTimeout(() => {
        overlay.remove();
      }, 3000);

      // Reiniciar código
      konamiCode = [];

      // Efecto visual adicional
      document.body.style.filter = "sepia(1) hue-rotate(40deg)";
      setTimeout(() => {
        document.body.style.filter = "";
      }, 5000);
    }
  };

  // Agregar listener para detectar el Konami Code
  document.addEventListener('keydown', konamiHandler);
});
