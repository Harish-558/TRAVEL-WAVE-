package com.example.travelwave.data

import androidx.compose.ui.graphics.Color
import com.example.travelwave.ui.TripData
import com.example.travelwave.ui.theme.GreenPrimary

object MockData {
    val extendedHotDeals = listOf(
        TripData("https://picsum.photos/id/1015/600/400", "Himalayan Trekking", "4.9", "(320)", "₹5,500", "/person", "Adventure", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1016/600/400", "Goa Beach Party", "4.7", "(850)", "₹3,500", "/person", "Beach", Color(0xFF3B82F6), 1f),
        TripData("https://picsum.photos/id/1018/600/400", "Kerala Houseboat Cruise", "4.8", "(410)", "₹6,000", "/night", "Relaxation", Color(0xFF10B981), 1f),
        TripData("https://picsum.photos/id/1036/600/400", "Ladakh Bike Expedition", "5.0", "(530)", "₹12,000", "/trip", "Road Trip", Color(0xFFF59E0B), 1f),
        TripData("https://picsum.photos/id/1043/600/400", "Andaman Scuba Diving", "4.9", "(620)", "₹4,500", "/dive", "Water Sports", Color(0xFF0EA5E9), 1f),
        TripData("https://picsum.photos/id/1044/600/400", "Rajasthan Desert Safari", "4.6", "(290)", "₹2,800", "/person", "Safari", Color(0xFFD97706), 1f),
        // Adding more to reach ~30
        TripData("https://picsum.photos/id/1019/600/400", "Spiti Valley Road Trip", "4.8", "(150)", "₹15,000", "/trip", "Road Trip", Color(0xFFF59E0B), 1f),
        TripData("https://picsum.photos/id/1020/600/400", "Meghalaya Caving Explore", "4.7", "(210)", "₹4,200", "/person", "Adventure", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1021/600/400", "Gokarna Beach Trek", "4.6", "(340)", "₹1,800", "/person", "Beach Trek", Color(0xFF3B82F6), 1f),
        TripData("https://picsum.photos/id/1022/600/400", "Ranthambore Tiger Safari", "4.9", "(420)", "₹3,500", "/safari", "Wildlife", Color(0xFF10B981), 1f),
        TripData("https://picsum.photos/id/1023/600/400", "Manali Snowboarding", "4.5", "(180)", "₹2,500", "/person", "Winter Sports", Color(0xFF0EA5E9), 1f),
        TripData("https://picsum.photos/id/1024/600/400", "Auli Skiing Camp", "4.8", "(250)", "₹8,500", "/trip", "Winter Sports", Color(0xFF0EA5E9), 1f),
        TripData("https://picsum.photos/id/1025/600/400", "Dandeli River Rafting", "4.7", "(310)", "₹1,500", "/rafting", "Water Sports", Color(0xFF0EA5E9), 1f),
        TripData("https://picsum.photos/id/1026/600/400", "Munnar Tea Estate Tour", "4.9", "(580)", "₹1,200", "/person", "Nature", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1027/600/400", "Darjeeling Toy Train", "4.8", "(620)", "₹500", "/person", "Heritage Ride", Color(0xFFF59E0B), 1f),
        TripData("https://picsum.photos/id/1028/600/400", "Kaziranga Rhino Safari", "4.7", "(390)", "₹2,800", "/safari", "Wildlife", Color(0xFF10B981), 1f),
        TripData("https://picsum.photos/id/1029/600/400", "Sikkim Monastery Tour", "4.8", "(270)", "₹5,000", "/trip", "Spiritual", Color(0xFFD97706), 1f),
        TripData("https://picsum.photos/id/1031/600/400", "Andaman Glass Bottom Boat", "4.6", "(410)", "₹800", "/person", "Family Fun", Color(0xFF3B82F6), 1f),
        TripData("https://picsum.photos/id/1032/600/400", "Hampi Ruins Exploration", "4.9", "(540)", "₹1,500", "/person", "Heritage Walk", Color(0xFFF59E0B), 1f),
        TripData("https://picsum.photos/id/1033/600/400", "Pondicherry French Quarter", "4.7", "(380)", "₹1,200", "/person", "City Walk", Color(0xFF10B981), 1f),
        TripData("https://picsum.photos/id/1035/600/400", "Coorg Coffee Estate Stay", "4.8", "(450)", "₹4,500", "/night", "Staycation", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1037/600/400", "Wayanad Treehouse Stay", "4.9", "(320)", "₹6,500", "/night", "Unique Stay", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1038/600/400", "Bandhavgarh Safari", "4.7", "(290)", "₹4,000", "/safari", "Wildlife", Color(0xFF10B981), 1f),
        TripData("https://picsum.photos/id/1039/600/400", "Tawang Monastery Visit", "4.8", "(180)", "₹8,000", "/trip", "Spiritual", Color(0xFFD97706), 1f),
        TripData("https://picsum.photos/id/1041/600/400", "Puri Jagannath Darshan", "4.9", "(850)", "₹500", "/person", "Pilgrimage", Color(0xFFF59E0B), 1f),
        TripData("https://picsum.photos/id/1042/600/400", "Rann of Kutch Festival", "4.8", "(520)", "₹7,500", "/trip", "Cultural Festival", Color(0xFFD97706), 1f),
        TripData("https://picsum.photos/id/1020/600/400", "Lonavala Weekend Getaway", "4.6", "(310)", "₹2,500", "/person", "Relaxation", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1021/600/400", "Matheran Toy Train", "4.7", "(250)", "₹1,200", "/person", "Heritage Ride", Color(0xFFF59E0B), 1f),
        TripData("https://picsum.photos/id/1022/600/400", "Panchgani Table Land", "4.8", "(180)", "₹1,500", "/person", "Sightseeing", Color(0xFF3B82F6), 1f)
    )

    val extendedTopPicks = listOf(
        TripData("https://upload.wikimedia.org/wikipedia/commons/thumb/7/74/Trayambakeshwar_Temple_VK.jpg/960px-Trayambakeshwar_Temple_VK.jpg", "Rishikesh River Rafting", "4.9", "(450)", "₹1,200", "/ person", "Guided Tour", GreenPrimary, 1f),
        TripData("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Munnar_Overview.jpg/960px-Munnar_Overview.jpg", "Munnar Tea Gardens Hike", "4.7", "(180)", "₹800", "/ hour", "Activity", Color.Transparent, 1f),
        TripData("https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/East_facade_Hawa_Mahal_Jaipur_from_ground_level_%28July_2022%29_-_img_01.jpg/960px-East_facade_Hawa_Mahal_Jaipur_from_ground_level_%28July_2022%29_-_img_01.jpg", "Jaipur Cooking Masterclass", "5.0", "(240)", "₹2,500", "/ person", "Workshop", Color.Transparent, 1f),
        // Adding more to reach ~30
        TripData("https://picsum.photos/id/1045/600/400", "Galle Fort Tour", "4.8", "(310)", "₹1,500", "/ person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1047/600/400", "Bali Sunrise Hike", "4.9", "(540)", "₹3,200", "/ person", "Activity", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1048/600/400", "Kyoto Temple Walk", "4.9", "(620)", "₹4,500", "/ person", "Guided Tour", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1049/600/400", "Phuket Island Hopping", "4.7", "(890)", "₹5,500", "/ trip", "Activity", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1051/600/400", "Dubai Desert Safari", "4.8", "(950)", "₹6,000", "/ person", "Adventure", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1052/600/400", "Santorini Sunset Cruise", "4.9", "(820)", "₹12,000", "/ couple", "Relaxation", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1054/600/400", "Swiss Alps Train Ride", "5.0", "(910)", "₹15,000", "/ person", "Sightseeing", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1056/600/400", "Rome Colosseum Tour", "4.8", "(780)", "₹4,200", "/ person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1057/600/400", "Paris Louvre Fast Track", "4.7", "(650)", "₹3,800", "/ person", "Museum", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1058/600/400", "London Eye Experience", "4.7", "(880)", "₹3,500", "/ person", "Sightseeing", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1060/600/400", "New York Helicopter Tour", "4.9", "(420)", "₹18,000", "/ person", "Adventure", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1061/600/400", "Grand Canyon Skywalk", "4.8", "(560)", "₹6,500", "/ person", "Activity", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1062/600/400", "Machu Picchu Trek", "5.0", "(340)", "₹25,000", "/ trip", "Adventure", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1063/600/400", "Amazon Jungle Safari", "4.8", "(280)", "₹15,000", "/ trip", "Wildlife", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1064/600/400", "Cairo Pyramids Tour", "4.7", "(620)", "₹5,500", "/ person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1066/600/400", "Cape Town Penguin Beach", "4.9", "(410)", "₹2,500", "/ person", "Wildlife", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1068/600/400", "Sydney Opera House Tour", "4.8", "(590)", "₹3,200", "/ person", "Sightseeing", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1069/600/400", "Great Barrier Reef Snorkel", "4.9", "(750)", "₹9,500", "/ trip", "Water Sports", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1070/600/400", "Auckland Sky Jump", "4.8", "(210)", "₹14,000", "/ person", "Adventure", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1071/600/400", "Fiji Island Hop", "4.9", "(380)", "₹12,500", "/ trip", "Relaxation", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1072/600/400", "Maldives Overwater Villa", "5.0", "(920)", "₹45,000", "/ night", "Luxury", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1073/600/400", "Seychelles Beach Picnic", "4.8", "(180)", "₹8,500", "/ couple", "Romantic", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1074/600/400", "Mauritius Catamaran Cruise", "4.7", "(290)", "₹6,500", "/ person", "Activity", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1075/600/400", "Bora Bora Shark Dive", "4.9", "(150)", "₹18,500", "/ person", "Adventure", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1076/600/400", "Tahiti Pearl Farm Tour", "4.6", "(120)", "₹4,200", "/ person", "Activity", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1077/600/400", "Hawaii Volcano Tour", "4.8", "(340)", "₹9,500", "/ person", "Adventure", GreenPrimary, 1f),
        TripData("https://picsum.photos/id/1078/600/400", "Fiji Scuba Diving", "4.9", "(280)", "₹11,500", "/ dive", "Water Sports", Color(0xFF0EA5E9), 1f)
    )

    val extendedPopularDestinations = listOf(
        TripData("https://picsum.photos/id/1055/400/300", "Varanasi Ganges Tour", "4.8", "(510)", "₹1,000", "/person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1059/400/300", "Jaipur Palaces Visit", "4.9", "(320)", "₹3,000", "/person", "City Tour", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1040/400/300", "Agra Taj Mahal", "5.0", "(950)", "₹2,000", "/person", "Monument", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1033/400/300", "Mysore Royal Tour", "4.7", "(410)", "₹1,500", "/person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1067/400/300", "Mumbai City Tour", "4.8", "(620)", "₹2,500", "/person", "City Tour", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1065/400/300", "Delhi Heritage Walk", "4.6", "(380)", "₹1,200", "/person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1050/400/300", "Udaipur Lake Tour", "4.9", "(540)", "₹3,500", "/person", "Nature", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/1053/400/300", "Chennai Temple Trail", "4.7", "(400)", "₹1,800", "/person", "Temple Run", Color.Transparent, 1f),
        // Adding more to reach ~30
        TripData("https://picsum.photos/id/100/400/300", "Kolkata Tram Ride", "4.6", "(280)", "₹800", "/person", "City Tour", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/101/400/300", "Hyderabad Biryani Trail", "4.9", "(920)", "₹1,500", "/person", "Food Walk", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/103/400/300", "Bengaluru Pub Crawl", "4.8", "(650)", "₹2,800", "/person", "Nightlife", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/104/400/300", "Pune Fort Trek", "4.7", "(310)", "₹1,200", "/person", "Adventure", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/106/400/300", "Ahmedabad Heritage Walk", "4.7", "(240)", "₹900", "/person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/107/400/300", "Chandigarh Garden Tour", "4.6", "(180)", "₹600", "/person", "Nature", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/108/400/300", "Amritsar Golden Temple", "5.0", "(1200)", "₹500", "/person", "Pilgrimage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/109/400/300", "Srinagar Shikara Ride", "4.9", "(850)", "₹1,200", "/ride", "Romantic", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/110/400/300", "Shimla Mall Road Walk", "4.8", "(540)", "₹0", "/person", "City Walk", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/111/400/300", "Darjeeling Tea Tasting", "4.9", "(380)", "₹800", "/person", "Food Walk", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/112/400/300", "Gangtok Cable Car", "4.7", "(290)", "₹400", "/person", "Activity", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/113/400/300", "Shillong Waterfall Tour", "4.8", "(410)", "₹1,500", "/person", "Nature", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/114/400/300", "Pondicherry Cafe Hopping", "4.8", "(560)", "₹1,800", "/person", "Food Walk", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/115/400/300", "Madurai Temple Visit", "4.9", "(720)", "₹500", "/person", "Pilgrimage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/116/400/300", "Kanyakumari Sunset Tour", "4.8", "(890)", "₹600", "/person", "Sightseeing", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/117/400/300", "Ooty Train Journey", "4.9", "(950)", "₹800", "/person", "Heritage Ride", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/118/400/300", "Kodaikanal Lake Boating", "4.7", "(380)", "₹300", "/person", "Activity", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/119/400/300", "Mahabaleshwar Strawberry Farm", "4.8", "(420)", "₹500", "/person", "Nature", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/120/400/300", "Lonavala Cave Tour", "4.6", "(310)", "₹800", "/person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/121/400/300", "Goa Church Tours", "4.7", "(580)", "₹1,000", "/person", "Heritage", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/122/400/300", "Rishikesh Yoga Retreat", "4.9", "(430)", "₹5,000", "/person", "Spiritual", Color.Transparent, 1f),
        TripData("https://picsum.photos/id/123/400/300", "Alibaug Beach Hopping", "4.6", "(210)", "₹1,200", "/person", "Beach", Color.Transparent, 1f)
    )

    val exploreTrips = listOf(
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Doodhsagar_Fall.jpg/960px-Doodhsagar_Fall.jpg",
            title = "Dudhsagar Waterfalls Trek",
            rating = "4.9",
            reviews = "(320)",
            price = "₹2,500",
            unit = "/person",
            tag = "Adventure",
            tagColor = GreenPrimary,
            aspectRatio = 0.75f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ee/House_Boat_DSW.jpg/960px-House_Boat_DSW.jpg",
            title = "Kerala Backwaters Houseboat",
            rating = "4.8",
            reviews = "(850)",
            price = "₹8,000",
            unit = "/night",
            tag = "Cruise",
            tagColor = Color(0xFF3B82F6),
            aspectRatio = 1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Taj_Mahal_%28Edited%29.jpeg/960px-Taj_Mahal_%28Edited%29.jpeg",
            title = "Taj Mahal Sunrise Tour",
            rating = "5.0",
            reviews = "(1.2k)",
            price = "₹1,500",
            unit = "/person",
            tag = "Temple & Heritage",
            tagColor = Color(0xFFA855F7),
            aspectRatio = 0.8f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Solang_Valley_%2CManali%2C_Himachal_Pardes%2C_India.JPG/960px-Solang_Valley_%2CManali%2C_Himachal_Pardes%2C_India.JPG",
            title = "Manali Snow Valley Trek",
            rating = "4.7",
            reviews = "(210)",
            price = "₹4,500",
            unit = "/person",
            tag = null,
            tagColor = Color.Transparent,
            aspectRatio = 1.33f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Munnar_Overview.jpg/960px-Munnar_Overview.jpg",
            title = "Munnar Tea Gardens Hike",
            rating = "4.9",
            reviews = "(156)",
            price = "₹1,200",
            unit = "/person",
            tag = "Hiking",
            tagColor = Color(0xFFF97316),
            aspectRatio = 1f
        ),
         TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/An_aerial_view_of_Madurai_city_from_atop_of_Meenakshi_Amman_temple.jpg/960px-An_aerial_view_of_Madurai_city_from_atop_of_Meenakshi_Amman_temple.jpg",
            title = "Meenakshi Amman Temple Tour",
            rating = "4.9",
            reviews = "(440)",
            price = "₹800",
            unit = "/person",
            tag = null,
            tagColor = Color.Transparent,
            aspectRatio = 0.75f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/dd/Wide_angle_of_Galigopuram_of_Virupaksha_Temple%2C_Hampi_%2804%29_%28cropped%29.jpg/960px-Wide_angle_of_Galigopuram_of_Virupaksha_Temple%2C_Hampi_%2804%29_%28cropped%29.jpg",
            title = "Hampi Ruins Exploration",
            rating = "4.8",
            reviews = "(620)",
            price = "₹1,500",
            unit = "/person",
            tag = "Heritage",
            tagColor = Color(0xFFA855F7),
            aspectRatio = 1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4d/Leh_City_seen_from_Shanti_Stupa.JPG/960px-Leh_City_seen_from_Shanti_Stupa.JPG",
            title = "Leh Ladakh Bike Expedition",
            rating = "5.0",
            reviews = "(890)",
            price = "₹15,000",
            unit = "/person",
            tag = "Adventure",
            tagColor = Color(0xFFEF4444),
            aspectRatio = 1.2f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/ISS054-E-7809_-_View_of_Earth_%28cropped%29.jpg/960px-ISS054-E-7809_-_View_of_Earth_%28cropped%29.jpg",
            title = "Pangong Tso Camping",
            rating = "4.9",
            reviews = "(750)",
            price = "₹4,500",
            unit = "/person",
            tag = "Camping",
            tagColor = Color(0xFF3B82F6),
            aspectRatio = 0.8f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a4/Mysore_Palace_Morning.jpg/960px-Mysore_Palace_Morning.jpg",
            title = "Mysore Palace Heritage Walk",
            rating = "4.7",
            reviews = "(1.1k)",
            price = "₹500",
            unit = "/person",
            tag = "Culture",
            tagColor = Color(0xFFEAB308),
            aspectRatio = 1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Radha_Nagar_beach%2C_Havelock_Island%2C_Andamn%2C_India-_Sun_set_view.jpg/960px-Radha_Nagar_beach%2C_Havelock_Island%2C_Andamn%2C_India-_Sun_set_view.jpg",
            title = "Radhanagar Beach Retreat",
            rating = "5.0",
            reviews = "(540)",
            price = "₹5,000",
            unit = "/person",
            tag = "Beach",
            tagColor = Color(0xFF0EA5E9),
            aspectRatio = 0.75f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/9/96/DarjeelingTrainFruitshop_%282%29.jpg/960px-DarjeelingTrainFruitshop_%282%29.jpg",
            title = "Darjeeling Himalayan Railway",
            rating = "4.8",
            reviews = "(920)",
            price = "₹1,800",
            unit = "/person",
            tag = "Experience",
            tagColor = Color(0xFF8B5CF6),
            aspectRatio = 1.33f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Beauty_of_Kaziranga_National_Park.jpg/960px-Beauty_of_Kaziranga_National_Park.jpg",
            title = "Kaziranga Rhino Safari",
            rating = "4.9",
            reviews = "(310)",
            price = "₹2,500",
            unit = "/person",
            tag = "Wildlife",
            tagColor = Color(0xFF22C55E),
            aspectRatio = 1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/6/65/Gujarat_Gulfs.jpg",
            title = "Great Rann of Kutch Festival",
            rating = "4.8",
            reviews = "(280)",
            price = "₹3,500",
            unit = "/person",
            tag = "Festival",
            tagColor = Color(0xFFF43F5E),
            aspectRatio = 0.8f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c3/Ajanta_%2863%29.jpg/960px-Ajanta_%2863%29.jpg",
            title = "Ajanta & Ellora Caves",
            rating = "4.9",
            reviews = "(800)",
            price = "₹1,200",
            unit = "/person",
            tag = "History",
            tagColor = Color(0xFFD97706),
            aspectRatio = 1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/Mamallapuram_view.jpg/960px-Mamallapuram_view.jpg",
            title = "Mahabalipuram Monuments",
            rating = "4.7",
            reviews = "(410)",
            price = "₹600",
            unit = "/person",
            tag = "Architecture",
            tagColor = Color(0xFF64748B),
            aspectRatio = 0.75f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Ooty_lake.jpg/960px-Ooty_lake.jpg",
            title = "Ooty Lake & Botanical Gardens",
            rating = "4.6",
            reviews = "(670)",
            price = "₹900",
            unit = "/person",
            tag = "Nature",
            tagColor = Color(0xFF10B981),
            aspectRatio = 1.25f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Golden_Temple_Amritsar_Gurudwara_%28cropped%29.jpg/960px-Golden_Temple_Amritsar_Gurudwara_%28cropped%29.jpg",
            title = "Golden Temple Amritsar",
            rating = "5.0",
            reviews = "(2.5k)",
            price = "Free",
            unit = "",
            tag = "Spiritual",
            tagColor = Color(0xFFF59E0B),
            aspectRatio = 1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/Evening_view%2C_City_Palace%2C_Udaipur.jpg/960px-Evening_view%2C_City_Palace%2C_Udaipur.jpg",
            title = "Udaipur City Palace Sunset",
            rating = "4.9",
            reviews = "(1.3k)",
            price = "₹800",
            unit = "/person",
            tag = "Royal",
            tagColor = Color(0xFF8B5CF6),
            aspectRatio = 0.8f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7e/Agra_03-2016_10_Agra_Fort.jpg/960px-Agra_03-2016_10_Agra_Fort.jpg",
            title = "Agra Fort Historical Tour",
            rating = "4.7",
            reviews = "(1.8k)",
            price = "₹650",
            unit = "/person",
            tag = "Heritage",
            tagColor = Color(0xFFB45309),
            aspectRatio = 1.1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/Fatehput_Sikiri_Buland_Darwaza_gate_2010.jpg/960px-Fatehput_Sikiri_Buland_Darwaza_gate_2010.jpg",
            title = "Fatehpur Sikri Excursion",
            rating = "4.6",
            reviews = "(900)",
            price = "₹500",
            unit = "/person",
            tag = "History",
            tagColor = Color(0xFF475569),
            aspectRatio = 1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/23/Sundarban_Tiger.jpg/960px-Sundarban_Tiger.jpg",
            title = "Sundarbans Tiger Reserve",
            rating = "4.8",
            reviews = "(450)",
            price = "₹3,200",
            unit = "/person",
            tag = "Wildlife",
            tagColor = Color(0xFF15803D),
            aspectRatio = 0.75f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Boating_in_Kodaikanal_Lake_with_Mist.jpg/960px-Boating_in_Kodaikanal_Lake_with_Mist.jpg",
            title = "Kodaikanal Misty Lake Boating",
            rating = "4.7",
            reviews = "(520)",
            price = "₹400",
            unit = "/boat",
            tag = "Activity",
            tagColor = Color(0xFF3B82F6),
            aspectRatio = 1f
        ),
        TripData(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Alappuzha_Boat_Beauty_W.jpg/960px-Alappuzha_Boat_Beauty_W.jpg",
            title = "Alappuzha Backwater Cruise",
            rating = "4.9",
            reviews = "(870)",
            price = "₹5,500",
            unit = "/boat",
            tag = "Relaxation",
            tagColor = Color(0xFF0EA5E9),
            aspectRatio = 1.25f
        )
    )

    val companies = listOf(
        CompanyData(1, "Wanderlust", "https://picsum.photos/id/350/150/150"),
        CompanyData(2, "Nomad Voyages", "https://picsum.photos/id/352/150/150"),
        CompanyData(3, "GlobeTrotters", "https://picsum.photos/id/353/150/150"),
        CompanyData(4, "Horizon Tours", "https://picsum.photos/id/354/150/150"),
        CompanyData(5, "Explore & Co", "https://picsum.photos/id/355/150/150"),
        CompanyData(6, "Tripster", "https://picsum.photos/id/356/150/150"),
        CompanyData(7, "Odyssey Travels", "https://picsum.photos/id/357/150/150"),
        CompanyData(8, "Venture Out", "https://picsum.photos/id/358/150/150"),
        CompanyData(9, "Pathfinders", "https://picsum.photos/id/360/150/150"),
        CompanyData(10, "Discoveries", "https://picsum.photos/id/361/150/150")
    )

    fun getTripsForCompany(companyId: Int): List<TripData> {
        val baseTrips = exploreTrips.shuffled(java.util.Random(companyId.toLong())).take(15)
        return baseTrips.map { trip ->
            val priceNum = trip.price.replace(Regex("[^0-9]"), "").toIntOrNull() ?: 2000
            val newPrice = Math.max(500, priceNum + (companyId * 150) - 500)
            trip.copy(price = "₹%,d".format(newPrice))
        }
    }

    fun getComparisonsForTrip(trip: TripData): List<CompanyPriceComparison> {
        val tripBasePrice = trip.price.replace(Regex("[^0-9]"), "").toIntOrNull() ?: 2000
        val random = java.util.Random(trip.title.hashCode().toLong())
        val subsetSize = random.nextInt(3) + 3 // Randomly 3 to 5 companies
        val shuffledCompanies = companies.shuffled(random).take(subsetSize)
        
        return shuffledCompanies.map { company ->
            // Use company ID as a deterministic modifier against the base price
            val companyPriceMod = (company.id * 150) - 500
            val newPrice = Math.max(500, tripBasePrice + companyPriceMod)
            CompanyPriceComparison(company, "₹%,d".format(newPrice))
        }.sortedBy { it.price.replace(Regex("[^0-9]"), "").toInt() } // Sort by cheapest price
    }
}

data class CompanyPriceComparison(
    val company: CompanyData,
    val price: String
)
