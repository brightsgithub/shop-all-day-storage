package com.shopallday.storage.infra.initializers.data;

import com.shopallday.storage.domain.models.Brand;
import com.shopallday.storage.domain.models.Product;
import com.shopallday.storage.domain.models.ProductType;
import com.shopallday.storage.domain.repository.BrandRepository;
import com.shopallday.storage.domain.repository.ProductTypeRepository;
import com.shopallday.storage.domain.repository.ProductsRepository;
import com.shopallday.storage.domain.repository.RepositoryManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductData implements DataHelper {

    private final ProductsRepository productsRepository;
    private final ProductTypeRepository productTypeRepository;
    private final BrandRepository brandRepository;
    private final RepositoryManager repositoryManager;

    public ProductData(
            ProductsRepository productsRepository,
            ProductTypeRepository productTypeRepository,
            BrandRepository brandRepository,
            RepositoryManager repositoryManager
    ) {
        this.productsRepository = productsRepository;
        this.productTypeRepository = productTypeRepository;
        this.brandRepository = brandRepository;
        this.repositoryManager = repositoryManager;
    }

    private interface ProductTypeNames {
        String TV = "TV";
        String DESKTOP_PC = "Desktop PC";
         String LAPTOP = "Laptop";
         String MOBILE = "Mobile";
         String MICROWAVE = "Microwave";
         String OVEN = "Oven";
         String WASHING_MACHINE = "Washing machine";
         String DISHWASHER = "Dishwasher";
         String DINING_TABLE = "Dining table";
         String TV_STAND = "TV stand";
         String SHOWER = "Shower";
         String SINK = "Sink";
         String BATH = "Bath";
    }

    private interface BrandNames {
        String SAMSUNG = "Samsung";
        String HP = "HP";
        String APPLE = "Apple";
        String ACER = "ACER";
        String LENOVO = "LENOVO";
        String GOOGLE = "Google";
        String BOSCH = "BOSCH";
        String PANASONIC = "PANASONIC";
        String BOASTAD = "BOASTAD";
        String HEMNES = "HEMNES";
        String LANEBERG = "LANEBERG";
    }




    @Override
    public void create() {
        List<Product> products = new ArrayList<>();

        List<ProductType> productTypes = productTypeRepository.findAllProductTypes();
        List<Brand> brands = brandRepository.findAllBrands();

        ProductType productTypeLaptop = filterProductsByTypeName(productTypes, ProductTypeNames.LAPTOP);
        ProductType productTypeTv = filterProductsByTypeName(productTypes, ProductTypeNames.TV);
        ProductType productTypeDesktopPC = filterProductsByTypeName(productTypes, ProductTypeNames.DESKTOP_PC);
        ProductType productTypeWashingMachine = filterProductsByTypeName(productTypes, ProductTypeNames.WASHING_MACHINE);
        ProductType productTypeMicrowave = filterProductsByTypeName(productTypes, ProductTypeNames.MICROWAVE);
        ProductType productTypeOven = filterProductsByTypeName(productTypes, ProductTypeNames.OVEN);
        ProductType productTypeDishwasher = filterProductsByTypeName(productTypes, ProductTypeNames.DISHWASHER);
        ProductType productTypeTVStand = filterProductsByTypeName(productTypes, ProductTypeNames.TV_STAND);
        ProductType productTypeDiningTable = filterProductsByTypeName(productTypes, ProductTypeNames.DINING_TABLE);
        ProductType productTypeBath = filterProductsByTypeName(productTypes, ProductTypeNames.BATH);
        ProductType productTypeSink = filterProductsByTypeName(productTypes, ProductTypeNames.SINK);
        ProductType productTypeMobile = filterProductsByTypeName(productTypes, ProductTypeNames.MOBILE);

        Brand lenovoBrand = filterBrandByName(brands, BrandNames.LENOVO);
        Brand appleBrand = filterBrandByName(brands, BrandNames.APPLE);
        Brand samsungBrand = filterBrandByName(brands, BrandNames.SAMSUNG);
        Brand brandHP = filterBrandByName(brands, BrandNames.HP);
        Brand brandACER = filterBrandByName(brands, BrandNames.ACER);
        Brand boschBrand = filterBrandByName(brands, BrandNames.BOSCH);
        Brand panasonicBrand = filterBrandByName(brands, BrandNames.PANASONIC);
        Brand brandBOASTAD = filterBrandByName(brands, BrandNames.BOASTAD);
        Brand brandHEMNES = filterBrandByName(brands, BrandNames.HEMNES);
        Brand brandLANEBERG = filterBrandByName(brands, BrandNames.LANEBERG);
        Brand googleBrand = filterBrandByName(brands, BrandNames.GOOGLE);


        addTVProducts(productTypeTv, samsungBrand, products);
        addDesktopPC(productTypeDesktopPC, brandHP, brandACER, products);
        addLaptops(productTypeLaptop, lenovoBrand, appleBrand, brandHP, products);
        addMobile(productTypeMobile, googleBrand, appleBrand, samsungBrand, products);
        addAppliances(productTypeWashingMachine, productTypeMicrowave, productTypeOven, productTypeDishwasher, boschBrand, panasonicBrand, products);
        addFurniture(productTypeTVStand, productTypeDiningTable, brandBOASTAD, brandHEMNES, brandLANEBERG, products);
        addBathroom(productTypeBath, productTypeSink, brandLANEBERG, products);

        productsRepository.createProducts(products, repositoryManager);
    }

    @Override
    public void print() {
        System.out.println("print getAllProductsUseCase called...");
        for(Product product: productsRepository.findAllProducts()) {
            System.out.println(product);
        }
        System.out.println("print getAllProductsUseCase finished");
    }

    private void addMobile(
            ProductType productTypeMobile, Brand googleBrand,
            Brand appleBrand,
            Brand samsungBrand, List<Product> products
    ) {
        // Product 21 - Samsung Galaxy S24 Ultra - 512 GB (Mobile)
        Product product21 = new Product(
                null, // productId will be auto-generated
                productTypeMobile,
                samsungBrand,
                "SAMSUNG Galaxy S24 Ultra - 512 GB",
                "SAMSUNG Galaxy S24 Ultra - 512 GB Titanium",
                "With the Galaxy S24 Ultra, you get nothing but the best. There's a 200 MP camera on the back, a beautiful screen, and a top-of-the-line chip, all wrapped in a titanium frame. It doesn't get more premium than that.",
                "With the Galaxy S24 Ultra, you get nothing but the best. There's a 200 MP camera on the back, a beautiful screen, and a top-of-the-line chip, all wrapped in a titanium frame. It doesn't get more premium than that. But what makes it truly special is the power of AI. It's at your service 24/7 to help with everything from the small things to the big tasks. Need to make a phone call in another language? Tranquillo – Live Translate's got you. Writing an email to your boss but you can't nail that professional tone? Chat Assist will suggest the perfect business-friendly phrases as you type. Who's got time to read a whole news article these days? Note Assist spits out a bite-sized summary in seconds. But it's not just about productivity. The cameras get an AI overhaul, too. Combine the 200 MP camera system with Nitography AI and voila. The dark has never looked so bright. It doesn't even matter if somebody photobombed you. Photo Assist gets rid of them with a single tap. Gone! The powerhouse behind this magic is the Qualcomm Snapdragon 8 Gen 3 processor. It'll run your games at silky-smooth framerates with beautifully realistic graphics. And the cherry on top is the S Pen. It's hiding inside the phone, always charged and ready to help you sign a PDF or jot down notes.\n\n" +
                        "\n" +
                        "Good to know\n" +
                        "\n" +
                        "- Whether you're browsing or binging - the 6.8\" Quad HD+ AMOLED display is a treat to look at.\n" +
                        "- The refresh rate dynamically changes between 1 and 120 Hz to match the content and save battery life.\n" +
                        "- The display is just as readable in direct sunlight, thanks to the dazzling 2600 nit brightness.\n" +
                        "- With a selection of wide, ultra-wide and 2 telephoto lenses, you'll always get the perfect shot.\n" +
                        "- Nothing is out of reach for the AI-powered 100x Space Zoom.\n" +
                        "- The 12 MP front camera is perfect for selfies with your friends, and Zoom calls with your family.\n" +
                        "- Thanks to the massive 5000 mAh battery, it barely even needs charging breaks.\n" +
                        "- With support for WiFi 7, you'll have a super-fast and reliable internet connection.\n" +
                        "- Where's that sweater from? Circle to Search with Google lets you search objects in your photos just by circling them.\n" +
                        "- Facial recognition and fingerprint scanning keep your personal files safe, secure and for your eyes only.\n" +
                        "- 5G connectivity lets you stream films and shows on-the-go – even when you're on the bus to work.\n" +
                        "- With 45 W Super Fast Charging and wireless charging, you've got plenty of ways to top up the battery.\n" +
                        "- You can wirelessly charge your gadgets from the phone with Wireless PowerShare.\n" +
                        "- Splashes or rain showers don't need to worry you – the S24 Ultra has an IP68 rating."
        );
        products.add(product21);

// Product 22 - Apple iPhone 15 (Mobile)
        Product product22 = new Product(
                null, // productId will be auto-generated
                productTypeMobile,
                appleBrand,
                "APPLE iPhone 15",
                "APPLE iPhone 15 - 128 GB, Black",
                "There's no waiting around with Apple iPhone 15. The A16 Bionic chip running the show is a powerhouse that will make light work of any app or game.",
                "There's no waiting around with Apple iPhone 15. The A16 Bionic chip running the show is a powerhouse that will make light work of any app or game. It can do it all day long, too. With up to 26 hours of battery life, you can leave the charger at home. And when it's time to charge, the USB Type-C port can do it faster than ever. Need to capture the perfect moment before it passes? The 48 MP advanced dual camera system takes photos that are full of life and colour. And they'll look amazing on the 6.1\" Super Retina XDR display, just like everything else.\n\n" +
                        "\n" +
                        "Good to know\n" +
                        "\n" +
                        "- The Dynamic Island around the front camera expands and adapts to give you important notifications and controls.\n" +
                        "- Nail the perfect shot with automatic Portrait Mode and get up close and personal with the 2x Telephoto zoom.\n" +
                        "- The Photonic Engine helps capture more light across all the cameras for better image quality in dark environments.\n" +
                        "- With Night Mode, not even nightfall can stop you from taking beautiful photos.\n" +
                        "- Unleash your inner filmmaker with 4K HDR video and Cinematic Mode.\n" +
                        "- When you need a top-up, just pop on a wireless MagSafe charger.\n" +
                        "- Face ID is convenient and secure, no hints needed.\n" +
                        "- Dance in the rain or swim in the ocean – the IP68 water resistance will keep your iPhone safe.\n" +
                        "- If you're in a car accident, Crash Detection can automatically notify emergency services.\n" +
                        "- With 5G connection you'll be surfing faster than ever before."
        );
        products.add(product22);

// Product 23 - Google Pixel 7a (Mobile)
        Product product23 = new Product(
                null, // productId will be auto-generated
                productTypeMobile,
                googleBrand,
                "GOOGLE Pixel 7a",
                "GOOGLE Pixel 7a - 128 GB, Charcoal",
                "There's no waiting around with Google Pixel 7a. The Tensor chip running the show is a powerhouse that will make light work of any app or game.",
                "There's no waiting around with Google Pixel 7a. The Tensor chip running the show is a powerhouse that will make light work of any app or game. It can do it all day long, too. With up to 24 hours of battery life, you can leave the charger at home. And when it's time to charge, the USB Type-C port can do it faster than ever. Need to capture the perfect moment before it passes? The 50 MP advanced dual camera system takes photos that are full of life and colour. And they'll look amazing on the 6.4\" OLED display, just like everything else.\n\n" +
                        "\n" +
                        "Good to know\n" +
                        "\n" +
                        "- The Adaptive Display dynamically adjusts the refresh rate to match the content and save battery life.\n" +
                        "- With Night Sight, not even darkness can stop you from taking beautiful photos.\n" +
                        "- The Titan M2 security chip keeps your data safe and secure.\n" +
                        "- Unleash your inner filmmaker with 4K video and HDR+ Mode.\n" +
                        "- The Rear Top Shot feature captures a burst of photos and automatically suggests the best one.\n" +
                        "- Google Lens lets you search what you see, get stuff done faster, and interact with the world around you.\n" +
                        "- When you need to focus, Digital Wellbeing helps you disconnect and unwind.\n" +
                        "- Dance in the rain or swim in the ocean – the Pixel 7a has an IP67 water and dust resistance rating."
        );
        products.add(product23);

    }

    private void addBathroom(
            ProductType productTypeBath,
            ProductType productTypeSink,
            Brand brandLANEBERG,
            List<Product> products
    ) {
        // Product 18 - Ceramic Bath (LANEBERG)
        Product product18 = new Product(
                null, // productId will be auto-generated
                productTypeBath,
                brandLANEBERG,
                "LANEBERG Ceramic bath",
                "LANEBERG Ceramic bath, 130/190x80 cm",
                "The simple design of this bath will blend seamlessly into your bathroom area.",
                "The simple design of this bath will blend seamlessly into your bathroom area. And the smooth and simple extension lets you extend your dinner invitation without worrying about fitting everyone in."
        );
        products.add(product18);

        // Product 19 - Ceramic Bath (LANEBERG New)
        Product product19 = new Product(
                null, // productId will be auto-generated
                productTypeBath,
                brandLANEBERG,
                "LANEBERG Ceramic bath NEW",
                "LANEBERG Ceramic bath NEW, 130/190x80 cm",
                "The simple design of this bath will blend seamlessly into your bathroom area.",
                "The simple design of this bath will blend seamlessly into your bathroom area. And the smooth and simple extension lets you extend your dinner invitation without worrying about fitting everyone in."
        );
        products.add(product19);

        // Product 20 - Inset Sink, 1 Bowl (LANEBERG)
        Product product20 = new Product(
                null, // productId will be auto-generated
                productTypeSink,
                brandLANEBERG,
                "LANEBERG Inset sink, 1 bowl",
                "LANEBERG Inset sink, 1 bowl, white, 53x47 cm",
                "Ceramic is a heat-resistant and durable material that has been used by humans to make practical objects for thousands of years.",
                "Ceramic is a heat-resistant and durable material that has been used by humans to make practical objects for thousands of years. The base material is different types of clay that are oven-burned and become things like terracotta, stoneware, pottery and porcelain. At IKEA we mainly use ceramic for plant pots, porcelain, washbasins and kitchen sinks. Since it can be shaped and even glazed to the desired colour and shine, the possibilities of styles and looks are endless."
        );
        products.add(product20);
    }

    private void addFurniture(
            ProductType productTypeTVStand,
            ProductType productTypeDiningTable,
            Brand brandBOASTAD,
            Brand brandHEMNES,
            Brand brandLANEBERG,
            List<Product> products
    ) {
        // Product 14 - TV Bench (BOASTAD)
        Product product14 = new Product(
                null, // productId will be auto-generated
                productTypeTVStand,
                brandBOASTAD,
                "BOASTAD TV bench, black/oak veneer",
                "BOASTAD TV bench, black/oak veneer, 121x42x45 cm",
                "A modern TV bench with thin lines, creating a stylish look in your home.",
                "A modern TV bench with thin lines, creating a stylish look in your home. The glass doors allow remote control signals to pass through – and unruly cables are taken care of by the practical mesh hammock."
        );
        products.add(product14);

        // Product 15 - TV Bench (HEMNES)
        Product product15 = new Product(
                null, // productId will be auto-generated
                productTypeTVStand,
                brandHEMNES,
                "HEMNES TV bench, black-brown/light brown",
                "TV bench, black-brown/light brown, 148x47x57 cm",
                "Sustainable beauty from sustainably-sourced solid pine, a natural and renewable material that gets more beautiful with each passing year.",
                "Wood is the material most commonly associated with IKEA furniture, and for good reasons. It’s renewable, recyclable, durable, ages beautifully and it is an important part of our Scandinavian design heritage."
        );
        products.add(product15);

        // Product 16 - Extendable Dining Table (LANEBERG)
        Product product16 = new Product(
                null, // productId will be auto-generated
                productTypeDiningTable,
                brandLANEBERG,
                "LANEBERG Extendable table",
                "LANEBERG Extendable table, white, 130/190x80 cm",
                "The simple design of this dining table will blend seamlessly into your dining area.",
                "The simple design of this dining table will blend seamlessly into your dining area. And the smooth and simple extension lets you extend your dinner invitation without worrying about fitting everyone in."
        );
        products.add(product16);

        // Product 17 - Extendable Dining Table (LANEBERG New)
        Product product17 = new Product(
                null, // productId will be auto-generated
                productTypeDiningTable,
                brandLANEBERG,
                "LANEBERG Extendable table new",
                "LANEBERG Extendable table new, white, 130/190x80 cm",
                "The simple design of this dining table will blend seamlessly into your dining area.",
                "The simple design of this dining table will blend seamlessly into your dining area. And the smooth and simple extension lets you extend your dinner invitation without worrying about fitting everyone in."
        );
        products.add(product17);
    }

    private void addAppliances(
            ProductType productTypeWashingMachine,
            ProductType productTypeMicrowave,
            ProductType productTypeOven,
            ProductType productTypeDishwasher,
            Brand boschBrand,
            Brand panasonicBrand,
            List<Product> products
            ) {
        // Product 10 - Washing Machine
        Product product10 = new Product(
                null, // productId will be auto-generated
                productTypeWashingMachine,
                boschBrand,
                "BOSCH Series 4 WGG04409GB",
                "BOSCH Series 4 WGG04409GB 9 kg 1400 Spin Washing Machine",
                "Using less water doesn't mean worse results. This Bosch Series 4 washing machine has ActiveWater Plus which uses sensors to work out exactly how much water is needed so you won't waste water on smaller loads.",
                "Using less water doesn't mean worse results. This Bosch Series 4 washing machine has ActiveWater Plus which uses sensors to work out exactly how much water is needed so you won't waste water on smaller loads. The SpeedPerfect option cuts washing times by up to 65% - and gives you the same great results as longer cycles. Got some particularly dirty clothes? HygienePlus will kill up to 99.9% of germs in a 40°C wash. Good to know - The Anti-vibration sidewalls stops your washing machine from unnecessarily vibrating so it's super quiet - The VarioDrum system is gentle enough to avoid snagging your tights but powerful enough to clean even the grimiest garment - Get rid of any detergent residue with the Extra Rinse setting – perfect for those with sensitive skin - If you miss a sock or t-shirt while you're sorting the load, just use the Reload function to add it in to the wash"
        );
        products.add(product10);

        // Product 11 - Microwave
        Product product11 = new Product(
                null, // productId will be auto-generated
                productTypeMicrowave,
                panasonicBrand,
                "PANASONIC NN-CT56JBBPQ",
                "PANASONIC NN-CT56JBBPQ Combination Microwave",
                "Combination microwave to handle all of your cooking needs.",
                "Combination microwave to handle all of your cooking needs - Automatic programs to take the guesswork out of cooking - Slim design to fit into any kitchen - Large touch control panel to easily select your desired settings Combination microwave The Panasonic NN-CT56JBBPQ Combination Microwave is an ideal addition to any kitchen, offering you a variety of cooking options to suit your needs. Whether you use it as a microwave, an oven or a grill, you can prepare dinner with ease. Inverter technology delivers constant power for even cooking results, helping food to retain its nutrients, vitamins and flavours. The quartz grill lets you prepare a traditional grilled breakfast, or even a quick snack like cheese on toast. Automatic programs With 29 different automatic programs, the NN-CT56JBBPQ takes all the guesswork out of cooking. Whether you're warming up a bowl of porridge in the morning or defrosting meat for your evening meal, you can save yourself time and leave the calculations to the microwave. A convenient Junior Menu is ideal for preparing healthy meals such as vegetable fries and healthy flapjacks to keep the whole family happy. Slim design The slim-line design of the NN-CT56JBBPQ helps to preserve precious worktop space in your kitchen, while the large 34 cm diameter turntable gives you greater flexibility when cooking larger meals. Large touch control panel Cooking is effortless with the NN-CT56JBBPQ as you can use the large touch control panel to easily choose your desired settings for every dish. Accessories Liven up your microwave cooking with our range of microwave-safe accessories. Whether you need to warm up your soup for lunch or steam rice and vegetables for dinner, you'll find something to help you create exciting meals in your microwave."
        );
        products.add(product11);

        // Product 12 - Oven
        Product product12 = new Product(
                null, // productId will be auto-generated
                productTypeOven,
                boschBrand,
                "BOSCH Series 2 HHF113BR0B",
                "BOSCH Series 2 HHF113BR0B Electric Oven - Stainless Steel",
                "Using less water doesn't mean worse results. This Bosch Series 4 washing machine has ActiveWater Plus which uses sensors to work out exactly how much water is needed so you won't waste water on smaller loads.",
                "Whether you're baking your nan a birthday cake or roasting Sunday dinner, get perfect results thanks to the Bosch Series 2 HHF113BR0B Electric Oven and its 3D Hotair system. Distributing heat quickly and evenly for up to three shelves at a time, you can cook your main and dessert without the flavours intermingling. Grill your favourite foods Enjoy grilled food, even in the middle of winter thanks to the integrated grill. Prepare a delicious lunch for everyone or add the finishing touches to an evening meal. Keep the kids safe This appliance is perfect for families with young kids, as it includes a child-proof lock on the oven to prevent your curious children from accidentally burning themselves. Prepare dinner for the whole family with complete peace of mind. Save on utilities Save money on utilities thanks to the efficient energy rating of the HHF113BR0B. With an energy rating of A, this oven lets you keep extra cash in your wallet when utilities come, and is friendly to the environment too."
        );
        products.add(product12);

        // Product 13 - Dishwasher
        Product product13 = new Product(
                null, // productId will be auto-generated
                productTypeDishwasher,
                boschBrand,
                "BOSCH Series 2 SMV2ITX18G",
                "BOSCH Series 2 SMV2ITX18G Full-size Fully Integrated WiFi-enabled Dishwasher",
                "Take the guesswork out of cleaning your dishes with the Bosch Series 2 SMV2ITX18G Full-size Fully Integrated WiFi-enabled Dishwasher.",
                "Take the guesswork out of cleaning your dishes with the Bosch Series 2 SMV2ITX18G Full-size Fully Integrated WiFi-enabled Dishwasher. Its Auto Programs automatically adjust the temperature and rinse time based on the type of load. You'll get sparkling results without wasting money on excess water or electricity. Home Connect With the Home Connect app, you can control the Bosch SMV2ITX18G with your smartphone or tablet. Adjust the settings from anywhere in your home, or add favourite programs to the quick selection. There's even a tab counter that will remind you when it's time to buy more tablets. Silence on demand When you need some quiet time, reduce the dishwasher noise with the Silence function on the app. It lowers noise levels for up to 30 minutes, so you can finish your conversation without the sound of the dishwasher in the background. ExtraDry function With the ExtraDry function you can say goodbye to finishing the job with a tea towel. Even difficult-to-dry items like plastic cups and boxes will come out dry and ready to use. InfoLight You'll always know when your dishes are done, thanks to the handy InfoLight feature. It projects a red light onto the floor when the dishwasher is running, so you won't accidentally open the door halfway through the cycle."
        );

        products.add(product13);

    }

    private void addLaptops(ProductType productTypeLaptop, Brand lenovoBrand, Brand appleBrand, Brand brandHP, List<Product> products) {
        // Product 7
        Product product7 = new Product(
                null, // productId will be auto-generated
                productTypeLaptop,
                lenovoBrand,
                "LENOVO IdeaPad 3i 15.6\" Laptop - Intel® Core™ i3",
                "LENOVO IdeaPad 3i 15.6\" Laptop - Intel® Core™ i3, 128 GB SSD",
                "Lenovo IdeaPad 3i. With an HD webcam and smart noise cancellation, your colleagues can focus on what you're saying",
                "Whether you're catching up with friends or stuck in an important work call, you'll look and sound great on this Lenovo IdeaPad 3i. With an HD webcam and smart noise cancellation, your colleagues can focus on what you're saying. You can rely on dual-band AC WiFi to keep your calls lag-free too, and the Intel® Core™ i3 processor will make sure your laptop doesn't freeze while screen-sharing. Good to know - Your photos, music, and apps will load up quickly on the 128 GB SSD - With an HDMI, two USBs, and a Type-C port, you can hook up an external monitor and all your accessories - The webcam privacy shutter lets you decide when you want to be seen on camera"
        );
        products.add(product7);

        // Product 8
        Product product8 = new Product(
                null, // productId will be auto-generated
                productTypeLaptop,
                appleBrand,
                "APPLE MacBook Air 13.3\" (2020) - M1",
                "APPLE MacBook Air 13.3\" (2020) - M1, 256 GB SSD",
                "Work on anything, anywhere with the incredibly light and speedy Macbook Air 2020. The M1 chip is a game-changer.",
                "Work on anything, anywhere with the incredibly light and speedy Macbook Air 2020. The M1 chip is a game-changer. It's 3.5x faster than the previous Macbook Air, and packs in 8 CPU and 7 GPU cores so you can take on video-editing and gaming. Plus, it's incredibly power-efficient. The M1 lets you browse for up to 15 hours, or watch Apple TV for around 18 - that's a full flight from London to Sydney! Good to know - Power doesn't mean noise anymore – with the M1's improved thermal efficiency this Air doesn't even need a fan - Whether you're working, playing or streaming the Retina Display is a real treat - True Tone automatically adjusts the display's colours, so they stay true no matter your room's lighting - Apple's System on a Chip design means the SSD storage, processor and RAM all work as one for superfast loading - The image signal processor and Neural Engine willmake your friends' skin tones much more natural-looking on FaceTime calls - You've got Touch ID to quickly unlock your MacBook, sign in to apps, and make secure payments with Apple Pay"
        );
        products.add(product8);

        // Product 9
        Product product9 = new Product(
                null, // productId will be auto-generated
                productTypeLaptop,
                brandHP,
                "HP 15a-nb0502sa 15.6\" Chromebook Plus - Intel® Core™ i3",
                "HP 15a-nb0502sa 15.6\" Chromebook Plus - Intel® Core™ i3, 128 GB SSD, Silver",
                "All your must-have tools like Word and Excel are right here as apps. The Intel® Core™ i3 processor ",
                "Just because ChromeOS keeps things simple doesn't mean you have to make compromises. All your must-have tools like Word and Excel are right here as apps. The Intel® Core™ i3 processor has enough power to keep things running smoothly. Forget scrambling for outlets at cafes. Its battery lasts for up to 13 hours, so it's great for working on the go. And when you do run out of power, HP Fast Charge gets the laptop from 0 to 50% in just 45 minutes. Good to know - 8 GB of DDR5 RAM lets you run multiple apps at the same time and loads of browser tabs - Its Full HD screen has vibrant colours and wide viewing angles, so it's great for kicking back with a movie - The 128 GB SSD storage will give you speedy access to all your essentials - For connecting to monitors, you've got options – use the DisplayPort or one of the 2 USB Type-C ports - Load up websites and videos in no time thanks to WiFi 6 - Google's H1 security chip is just one of the reasons why Chromebooks are so resistant to viruses - Look your best in online meetings with the True Vision Full HD webcam - Set up your home office anywhere – the anti-glare display can handle a bit of sunlight"
        );
        products.add(product9);
    }

    private void addDesktopPC(ProductType productTypeDesktopPC, Brand brandHP, Brand brandACER, List<Product> products) {
        // Product 4
        Product product4 = new Product(
                null, // productId will be auto-generated
                productTypeDesktopPC,
                brandHP,
                "HP Slim S01-pF2012na Desktop PC",
                "HP Slim S01-pF2012na Desktop PC - Intel® Core™ i3, 256 GB SSD, Black",
                "Packing a 12th gen Intel® Core™ i3 processor, its four cores will breeze through most day-to-day tasks",
                "If you need a reliable workhorse for home office, study or even a spot of light gaming, this HP could be the ticket. Packing a 12th gen Intel® Core™ i3 processor, its four cores will breeze through most day-to-day tasks. For getting to files in a hurry the 256 GB SSD is a huge time-saver and you can easily pop another M.2 SSD in to expand your storage. And all of it's packed in a compact case, so it won't take up too much space on your table. Good to know - 8 GB of RAM is plenty for multi-tasking with Word, Excel and Chrome - The built-in disc drive means no more rummaging for that external one when you need to install something - Into video editing or gaming? You've got a PCIe x16 slot ready and waiting for a top-notch graphics card - Bluetooth lets you connect all your favourite wireless devices - It comes with a keyboard and mouse to get you up and running right away"
        );
        products.add(product4);

        // Product 5
        Product product5 = new Product(
                null, // productId will be auto-generated
                productTypeDesktopPC,
                brandHP,
                "HP Slim S01-pF2011na Desktop PC - Intel® Core™ i5",
                "HP Slim S01-pF2011na Desktop PC - Intel® Core™ i5, 256 GB SSD, Black",
                "Packing a 12th gen Intel® Core™ i3 processor, its four cores will breeze through most day-to-day tasks",
                "If you need a reliable workhorse for home office, study or even a spot of light gaming, this HP could be the ticket. Packing a 12th gen Intel® Core™ i3 processor, its four cores will breeze through most day-to-day tasks. For getting to files in a hurry the 256 GB SSD is a huge time-saver and you can easily pop another M.2 SSD in to expand your storage. And all of it's packed in a compact case, so it won't take up too much space on your table. Good to know - 8 GB of RAM is plenty for multi-tasking with Word, Excel and Chrome - The built-in disc drive means no more rummaging for that external one when you need to install something - Into video editing or gaming? You've got a PCIe x16 slot ready and waiting for a top-notch graphics card - Bluetooth lets you connect all your favourite wireless devices - It comes with a keyboard and mouse to get you up and running right away"
        );
        products.add(product5);

        // Product 6
        Product product6 = new Product(
                null, // productId will be auto-generated
                productTypeDesktopPC,
                brandACER,
                "ACER Revo Box RN96 Desktop PC",
                "ACER Revo Box RN96 Desktop PC - Intel® Core™ i5, 1 TB SSD, Black",
                "Packing a 12th gen Intel® Core™ i3 processor, its four cores will breeze through most day-to-day tasks",
                "Regain some space on your desk or set up a multimedia centre with the Acer Revo Box. It's super compact but still gives you a full array of ports, including USB-C, HDMI and DisplayPort. The 11th gen Intel® Core™ i5 processor will fly through apps like Word and Excel. And don't worry about storage - the 1 TB SSD lets you save everything you need and access it in a flash. Good to know - It's got 6 USBs, HDMI and DisplayPort to hook up all your peripherals and two monitors - With WiFi 6, you can get a solid internet connection, with less lag and drop out - Bluetooth 5 lets you roam free without worrying about your headset disconnecting - It comes with a wireless mouse and keyboard to get you up and running right away"
        );
        products.add(product6);
    }

    private static void addTVProducts(ProductType productTypeTv, Brand samsungBrand, List<Product> products) {
        // Product 1
        Product product1 = new Product(
                null,
                productTypeTv,
                samsungBrand,
                "Q60C QLED 4K HDR",
                "2023 Q60C QLED 4K HDR Smart TV",
                "Powered by Quantum Dot for a naturally bright and colourful picture",
                "Powered by Quantum Dot for a naturally bright and colourful picture Open up previously hidden depth and detail Warm and cool LEDs create a bright picture with bold contrast Sound that tracks the action on-screen Upgrade the picture and sound quality with a powerful 4K processor"
        );
        products.add(product1);

        // Product 2
        Product product2 = new Product(
                null, // productId will be auto-generated
                productTypeTv,
                samsungBrand,
                "N85C Neo QLED 4K HDR",
                "2023 QN85C Neo QLED 4K HDR Smart TV",
                "Powered by Quantum Dot for a naturally bright and colourful picture.",
                "Precisely controlled Mini LEDs create an incredibly sharp picture. Brightness, colour and detail boosted by our most intelligent AI-powered 4K processor. Cinematic sound reimagined with Dolby Atmos and 6 TV speakers. See the true beauty of every scene unveiled in exceptional detail, with bright colour and bold contrast."
        );
        products.add(product2);

        // Product 3
        Product product3 = new Product(
                null, // productId will be auto-generated
                productTypeTv,
                samsungBrand,
                "2023 QN90C Neo QLED 4K HDR Smart TV",
                "0% Finance available, view options.",
                "Powered by Quantum Dot for a naturally bright and colourful picture.",
                "Precisely controlled Mini LEDs create an incredibly sharp picture. Experience the brilliant picture from every angle, with minimised distracting glare. Brightness, colour and detail boosted by our most intelligent AI-powered 4K processor."
        );
        products.add(product3);
    }

    public static ProductType filterProductsByTypeName(List<ProductType> productTypeList, String typeName) {
        for (ProductType productType : productTypeList) {
            if (productType.getProductTypeName().equals(typeName)) {
                return productType;
            }
        }
        // Handle the case where no product with the given type name is found
        throw new IllegalArgumentException("ProductType with type name '" + typeName + "' not found");
    }

    public static Brand filterBrandByName(List<Brand> brandList, String brandName) {
        for (Brand brand : brandList) {
            if (brand.getBrandName().equals(brandName)) {
                return brand;
            }
        }
        // Handle the case where no Brand with the given type name is found
        throw new IllegalArgumentException("Brand with name '" + brandName + "' not found");
    }
}
