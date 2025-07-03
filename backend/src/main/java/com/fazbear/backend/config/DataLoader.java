package com.fazbear.backend.config;

import com.fazbear.backend.model.Product;
import com.fazbear.backend.model.Animatronic;
import com.fazbear.backend.model.Location;
import com.fazbear.backend.model.Event;
import com.fazbear.backend.repository.ProductRepository;
import com.fazbear.backend.repository.AnimatronicRepository;
import com.fazbear.backend.repository.LocationRepository;
import com.fazbear.backend.repository.EventRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
    
    // Constantes para strings repetidos
    private static final String FREDDY_IMAGE = "freddy.png";
    private static final String BONNIE_IMAGE = "bonnie.png";
    private static final String CHICA_IMAGE = "chica.png";
    private static final String FOXY_IMAGE = "foxy.png";
    private static final String PHONE_FORMAT = "(XXX) 555-FRED";

    private final ProductRepository productRepository;
    private final AnimatronicRepository animatronicRepository;
    private final LocationRepository locationRepository;
    private final EventRepository eventRepository;

    public DataLoader(
        ProductRepository productRepository,
        AnimatronicRepository animatronicRepository,
        LocationRepository locationRepository,
        EventRepository eventRepository
    ) {
        this.productRepository = productRepository;
        this.animatronicRepository = animatronicRepository;
        this.locationRepository = locationRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try {
            logger.info("🎪 Iniciando DataLoader para Fazbear's Pizza...");
            
            loadProducts();
            loadAnimatronics();
            loadLocations();
            loadEvents();
            
            logger.info("🎉 Datos precargados exitosamente para todos los modelos.");
        } catch (Exception e) {
            logger.error("❌ Error al cargar datos iniciales", e);
            throw e;
        }
    }

    private void loadProducts() {
        if (productRepository.count() == 0) {
            logger.info("🍕 Cargando productos iniciales...");
            
productRepository.save(new Product(
    "Freddy's Pepperoni Feast",
    "¡La estrella del show! Masa crujiente, salsa de tomate especial Fazbear, doble pepperoni y queso derretido con bordes dorados como el sombrero de Freddy.",
    10.99, "Pizza",
    "https://i.ibb.co/xSxGBwP1/pizza-freddy.jpg"));

productRepository.save(new Product(
    "Bonnie's Electric Blues",
    "¡Una explosión de color! Salsa de tomate azul vibrante (¡sí, azul!), mozzarella cremosa, aceitunas negras como sus ojos y un toque de ajo.",
    11.99, "Pizza",
    "https://i.ibb.co/TM8Kb9Jb/Bonnie-Pizza.jpg"));

productRepository.save(new Product(
    "Chica's Chicken Fiesta",
    "¡Para los amantes del pollo! Base de salsa BBQ, tierno pollo desmenuzado, maíz dulce y queso cheddar fundido. Incluye un cupcake de regalo si pides la familiar.",
    12.99, "Pizza",
    "https://i.ibb.co/TfPcjLt/pizza-chica.png"));

productRepository.save(new Product(
    "Foxy's Pirate Booty",
    "¡Peligrosamente deliciosa! Pepperoni en espiral como un remolino, jalapeños que arden como el fuego de un cañón, tocino crujiente y salsa de queso picante.",
    12.99, "Pizza",
    "https://i.ibb.co/C5SC28sv/pizza-foxy.jpg"));

productRepository.save(new Product(
    "Fazbear's Mega Fizz",
    "¡La bebida oficial de la banda! Refresco de cereza con burbujas que explotan como aplausos. Servido en un vaso coleccionable con los 4 animatrónicos.",
    3.99, "Bebida",
    "https://i.ibb.co/fVmTTNdp/bebida-cereza.png"));

productRepository.save(new Product(
    "Bonnie's Blue Bolt",
    "¡Energía eléctrica en cada sorbo! Limonada azul con gas, edulcorante secreto y un toque de frambuesa.",
    4.25, "Bebida",
    "https://i.ibb.co/7d9nWKsz/Bebida-bonnie.jpg"));

productRepository.save(new Product(
    "Chica's Sparkling Lemonade",
    "¡Dulce como su voz! Limonada rosa con trozos de fresa, burbujas de vainilla y un toque de miel.",
    3.75, "Bebida",
    "https://i.ibb.co/zhvM3kt9/bebida-chica.jpg"));

productRepository.save(new Product(
    "Freddy's Golden Cake",
    "¡Un clásico que nunca falla! Pastel de chocolate en forma de cabeza de Freddy, relleno de crema de avellana y decorado con glaseado dorado comestible.",
    5.50, "Postre",
    "https://i.ibb.co/tMgfLNTs/Fazbear-cake.png"));

productRepository.save(new Product(
    "Chica's Signature Cupcake",
    "¡El favorito de los niños! Cupcake de vainilla con frosting rosa brillante, ojos de chocolate y una cereza en la cima. ¡Incluye una velita de cumpleaños gratis!",
    2.99, "Postre",
    "https://i.ibb.co/d4pGN8LP/cupcake.png"));

            
            logger.info("✅ {} productos cargados exitosamente", productRepository.count());
        }
    }

    private void loadAnimatronics() {
        if (animatronicRepository.count() == 0) {
            logger.info("🤖 Cargando animatrónicos iniciales...");
            
            animatronicRepository.save(new Animatronic(
                "Freddy Fazbear",
                "Líder de la banda",
                "El líder carismático de la banda, siempre listo para el espectáculo.",
                FREDDY_IMAGE
            ));
            animatronicRepository.save(new Animatronic(
                "Bonnie",
                "Guitarrista",
                "El guitarrista de la banda, carismático y activo.",
                BONNIE_IMAGE
            ));
            animatronicRepository.save(new Animatronic(
                "Chica",
                "Chef",
                "La chef de la pizzería, dulce y siempre sonriente.",
                CHICA_IMAGE
            ));
            animatronicRepository.save(new Animatronic(
                "Foxy",
                "Pirata",
                "El pirata aventurero, siempre al acecho del público.",
                FOXY_IMAGE
            ));
            
            logger.info("✅ {} animatrónicos cargados exitosamente", animatronicRepository.count());
        }
    }

    private void loadLocations() {
        if (locationRepository.count() == 0) {
            logger.info("📍 Cargando ubicaciones iniciales...");
            
            locationRepository.save(new Location(
                "Freddy's Hurricane",
                "1987 Pizzaplex Avenue, Hurricane, Utah",
                PHONE_FORMAT.replace("XXX", "435"),
                37.1853, -111.6457
            ));
            locationRepository.save(new Location(
                "Freddy's New York",
                "123 Broadway, New York, NY",
                PHONE_FORMAT.replace("XXX", "212"),
                40.7128, -74.0060
            ));
            locationRepository.save(new Location(
                "Freddy's Los Angeles",
                "456 Hollywood Blvd, Los Angeles, CA",
                PHONE_FORMAT.replace("XXX", "323"),
                34.0522, -118.2437
            ));
            locationRepository.save(new Location(
                "Freddy's Chicago",
                "789 Michigan Ave, Chicago, IL",
                PHONE_FORMAT.replace("XXX", "312"),
                41.8781, -87.6298
            ));
            locationRepository.save(new Location(
                "Freddy's Miami",
                "321 Ocean Drive, Miami, FL",
                PHONE_FORMAT.replace("XXX", "305"),
                25.7617, -80.1918
            ));
            
            logger.info("✅ {} ubicaciones cargadas exitosamente", locationRepository.count());
        }
    }

    private void loadEvents() {
        if (eventRepository.count() == 0) {
            logger.info("🎉 Cargando eventos de ejemplo...");
            
            eventRepository.save(new Event(
                "Fiesta de Cumpleaños",
                "cliente1@fazbear.com",
                LocalDate.now().plusDays(7),
                15,
                "¡Queremos una fiesta temática de Freddy!"
            ));
            eventRepository.save(new Event(
                "Evento Corporativo",
                "empresa@ejemplo.com",
                LocalDate.now().plusMonths(1),
                30,
                "Necesitamos un evento para nuestro equipo de ventas"
            ));
            eventRepository.save(new Event(
                "Celebración Escolar",
                "escuela@ejemplo.com",
                LocalDate.now().plusWeeks(2),
                25,
                "Premiación de fin de año para estudiantes destacados"
            ));
            
            logger.info("✅ {} eventos cargados exitosamente", eventRepository.count());
        }
    }
}