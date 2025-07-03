// charts.js
// Gráficos con Chart.js - IDs corregidos para coincidir con stats.html

document.addEventListener("DOMContentLoaded", () => {
  // Gráfico de Ventas Mensuales
  const ctx1 = document.getElementById("ventasMensuales");
  if (ctx1) {
    new Chart(ctx1, {
      type: "line",
      data: {
        labels: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio"],
        datasets: [{
          label: "Ventas Mensuales ($)",
          data: [1200, 1900, 3000, 2800, 3200, 2900],
          backgroundColor: "rgba(251, 191, 36, 0.2)",
          borderColor: "#fbbf24",
          borderWidth: 3,
          fill: true,
          tension: 0.4
        }]
      },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Ventas Mensuales 2025',
            color: '#fbbf24'
          },
          legend: {
            labels: {
              color: '#f5f5f5'
            }
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            ticks: {
              color: '#f5f5f5'
            },
            grid: {
              color: '#404040'
            }
          },
          x: {
            ticks: {
              color: '#f5f5f5'
            },
            grid: {
              color: '#404040'
            }
          }
        }
      }
    });
  }

  // Gráfico de Ingredientes Populares
  const ctx2 = document.getElementById("ingredientesPopulares");
  if (ctx2) {
    new Chart(ctx2, {
      type: "doughnut",
      data: {
        labels: ["Queso Extra", "Pepperoni", "Vegetales", "Salsa Especial"],
        datasets: [{
          data: [35, 25, 20, 20],
          backgroundColor: ["#fbbf24", "#ef4444", "#10b981", "#3b82f6"],
          borderWidth: 2,
          borderColor: "#1f1f1f"
        }]
      },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Ingredientes Más Populares',
            color: '#fbbf24'
          },
          legend: {
            labels: {
              color: '#f5f5f5'
            }
          }
        }
      }
    });
  }

  // Gráfico de Popularidad de Animatrónicos
  const ctx3 = document.getElementById("popularidadAnimatronicos");
  if (ctx3) {
    new Chart(ctx3, {
      type: "bar",
      data: {
        labels: ["Freddy", "Chica", "Bonnie", "Foxy"],
        datasets: [{
          label: "Popularidad (%)",
          data: [90, 85, 80, 95],
          backgroundColor: ["#fbbf24", "#facc15", "#a855f7", "#ef4444"],
          borderWidth: 2,
          borderColor: "#1f1f1f"
        }]
      },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Popularidad de Animatrónicos',
            color: '#fbbf24'
          },
          legend: {
            labels: {
              color: '#f5f5f5'
            }
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            max: 100,
            ticks: {
              color: '#f5f5f5'
            },
            grid: {
              color: '#404040'
            }
          },
          x: {
            ticks: {
              color: '#f5f5f5'
            },
            grid: {
              color: '#404040'
            }
          }
        }
      }
    });
  }

  // Gráfico de Combo Favorito
  const ctx4 = document.getElementById("comboFavorito");
  if (ctx4) {
    new Chart(ctx4, {
      type: "polarArea",
      data: {
        labels: ["Pizza + Refresco", "Pizza + Postre", "Combo Familiar", "Especial Freddy"],
        datasets: [{
          data: [30, 25, 35, 40],
          backgroundColor: [
            "rgba(251, 191, 36, 0.8)",
            "rgba(168, 85, 247, 0.8)",
            "rgba(239, 68, 68, 0.8)",
            "rgba(16, 185, 129, 0.8)"
          ],
          borderWidth: 2,
          borderColor: "#1f1f1f"
        }]
      },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: 'Combos Más Vendidos',
            color: '#fbbf24'
          },
          legend: {
            labels: {
              color: '#f5f5f5'
            }
          }
        },
        scales: {
          r: {
            ticks: {
              color: '#f5f5f5',
              backdropColor: 'transparent'
            },
            grid: {
              color: '#404040'
            },
            angleLines: {
              color: '#404040'
            }
          }
        }
      }
    });
  }
});