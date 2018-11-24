package com.prototype.hackyeah2018.inserter;

import java.util.ArrayList;
import java.util.List;

import com.prototype.hackyeah2018.model.Medicine;
import com.prototype.hackyeah2018.model.Pharmacy;

public class MedicineGenerator {

    public static List<Medicine> getMedicines(Pharmacy pharmacy) {
        List<Medicine> medicines = new ArrayList<>();

        long i = 1;
        for(String name : getMedicineNames()) {
            Medicine medicine = new Medicine();

            medicine.setId(i++);
            medicine.setName(name);
            medicine.setAvailable(true);
            medicine.setPharmacyId(pharmacy.getId());

            medicines.add(medicine);
        }
        return medicines;
    }



    public static List<String> getMedicineNames() {

        List<String> names = new ArrayList<>();

        names.add("DOZ PRODUCT Czosnek bezzapachowy forte, kapsułki, 90 szt.");
        names.add("DOZ PRODUCT Tymianek i podbiał, pastylki do ssania bez cukru, 24 szt.");
        names.add("DOZ PRODUCT Tymianek i podbiał, pastylki do ssania, 24 szt.");
        names.add("ZIELNIK DOZ Rozgrzewająca herbatka, 2 g, 20 saszetek");
        names.add("ZIELNIK DOZ Szałwia, zioła do zaparzania, 1,2 g, 30 saszetek");
        names.add("DOZ PRODUCT, Antussimin, syrop, 120 ml");
        names.add("DOZ PRODUCT, Tussimin, syrop, 120 ml");
        names.add("ZIELNIK DOZ, Herbatka owocowa z owocami leśnymi, 80 g");
        names.add("Witamina C Labor 1000 mg Natural, saszetki, 10 szt.");
        names.add("Xylorin Express, spray do nosa, 20 ml");
        names.add("Olimp Gold-Vit C 2000 Shot, płyn w ampułkach, smak cytrynowy, 25 ml, 1 szt.");
        names.add("Olimp Gold-Vit C MED, tabletki do ssania, smak malinowy, 30 szt.");
        names.add("Echimunn C, proszek w saszetkach, 20 szt.");
        names.add("NORDISEPT, porost islandzki z witaminą C, pastylki do ssania, smak czarna porzeczka, 32 szt.");
        names.add("Rutinoscorbin, tabletki powlekane, 150 szt.");
        names.add("ENTitis, pastylki do ssania, smak truskawkowy, 30 szt.");
        names.add("Cerutin, 100 mg + 25 mg, tabletki powlekane, 125 szt.");
        names.add("Rutinoscorbin, tabletki powlekane, 90 szt.");
        names.add("Aromactiv+, plastry, 5 szt.");
        names.add("Gilbert NaCl 0.9%, 5 ml, fizjologiczny roztwór soli, 100 ampułek");
        names.add("Ibuprom Max, 400 mg, tabletki drażowane, 48 szt.");
        names.add("DOZ PRODUCT Testy ciążowe DUO PACK (strumieniowy + płytkowy), 1 opakowanie");
        names.add("DOZ PRODUCT, Antussimin, syrop, 120 ml");
        names.add("Bio-Oil, olejek na rozstępy i blizny, 200 ml");
        names.add("VitBaby D, kapsułki twist-off, 30 szt.");
        names.add("La Roche-Posay Cicaplast Baume B5, kojący balsam regenerujący, 100 ml");
        names.add("La Roche-Posay, Lipikar Gel Lavant, żel myjący, 400 ml");
        names.add("Tołpa Expert Kids, pasta do zębów dla dzieci 0-6 lat, 50 ml");
        names.add("Soraya Lactissima Ciąża i Połóg, emulsja do higieny intymnej, 300 ml");
        names.add("Humana 2, mleko następne, proszek, 800 g (puszka)");
        names.add("Humana Na Dobranoc Premium, mleko następne, proszek, 600 g");
        names.add("Humana 1, mleko początkowe, proszek, 350 g (puszka)");
        names.add("Humana HN, proszek, 300 g");
        names.add("Humana HA 1, hipoalergiczne mleko początkowe, proszek, 500 g");
        names.add("Humana 0, mleko modyfikowane, proszek, 400 g");
        names.add("Humana AR, proszek, 400 g");
        names.add("Vivomixx, krople doustne, 1 buteleczka, 5 ml");
        names.add("DOZ PRODUCT Kwas foliowy, tabletki, 90 szt.");
        names.add("Minionek, mydło do rąk, 250 ml");
        names.add("WITAMUSKI, tabletki do ssania, lekko musujące o smaku owocowym, 60 szt.");
        names.add("Minionek, żel pod prysznic, 350 ml");
        names.add("Xylorin Express, spray do nosa, 20 ml");
        names.add("Sanprobi Super Formuła, kapsułki, 40 szt.");
        names.add("Hepaslimin, tabletki, 30 szt.");
        names.add("Hydrominum, tabletki, 30 szt.");
        names.add("Colon C, proszek, 200 g");
        names.add("ZIELNIK DOZ Karczoch, tabletki powlekane, 60 szt.");
        names.add("Liporedium, tabletki, 60 szt.");
        names.add("Młody Jęczmień Forte Slim, tabletki, 60 szt.");
        names.add("DOZ PRODUCT Chrom, tabletki, 60 szt.");
        names.add("Olimp Therm Line Forte, kapsułki, 60 szt.");
        names.add("Wierzbownica drobnokwiatowa, tabletki powlekane, 60 szt.");
        names.add("Olimp Therm Line Fast, tabletki powlekane, 60 szt.");
        names.add("Ispagul S, proszek, 200 g");
        names.add("Kurkuma piperyna, kapsułki, 60 szt.");
        names.add("Młody Jęczmień, tabletki, 60 szt. (Colfarm)");
        names.add("ZIELNIK DOZ Dbam o oczyszczanie, herbatka ziołowa, 1,7 g, 10 szt.");
        names.add("Morwa Biała Forte, tabletki powlekane, 60 szt.");
        names.add("Plan by DOZ Detox, napój oczyszczający, cytryna, chilli, karczoch, aloe vera, 7 saszetek");
        names.add("ZIELNIK DOZ Dbam o kontrolę masy ciała, herbatka ziołowa, 2 g, 10 szt.");
        names.add("Para Farm, płyn doustny, 85 ml (15 ml GRATIS)");
        names.add("Plan by DOZ, Nasiona lnu, 250 g");
        names.add("Błonnik + żywe kultury bakterii, proszek, 350 g");
        names.add("Vichy Neovadiol Rose Platinum, różany krem wzmacniająco-rewitalizujący, 50 ml");
        names.add("La Roche-Posay Cicaplast Baume B5, kojący balsam regenerujący, 100 ml");
        names.add("Vichy, antyperspirant w kulce 48h, kuracja przeciw nadmiernemu poceniu, 50 ml");
        names.add("Bio-Oil, olejek na rozstępy i blizny, 200 ml");
        names.add("Vichy, antyperspirant w kulce do skóry wrażliwej lub po depilacji, 50 ml");
        names.add("Dermedic Antipersp R, antyperspirant, roll-on skóra wrażliwa, 60 g");
        names.add("Vichy Stress Resist, antyperspirant 72h, intensywna kuracja przeciw poceniu się, 50 ml");
        names.add("Dermedic Hydrain 3, serum nawadniające, 30 ml");
        names.add("Biovax A + E, serum wzmacniające włosy, 15 ml");
        names.add("Dermedic Emolient Linum, krem do rąk, regenerujący, 100 g");
        names.add("Biovax Silk, jedwab w płynie, 15 ml");
        names.add("La Roche-Posay, Lipikar Gel Lavant, żel myjący, 400 ml");
        names.add("Biovax, serum odżywcze do paznokci, 15 ml");
        names.add("Tołpa Expert Kids, pasta do zębów dla dzieci 0-6 lat, 50 ml");
        names.add("Dermedic Hydrain 3, żel kremowy do mycia, 200 ml");
        names.add("Dermedic Normacne Preventi, krem regulująco-oczyszczający na noc, 55 g");
        names.add("Dermedic Normacne Therapy, preparat punktowy, skóra trądzikowa, 15 g");
        names.add("Tołpa Expert Junior, płyn do płukania ust dla dzieci 6-12 lat, czerwone owoce, 250 ml");
        names.add("Avene Eau Thermale Cleanance, woda micelarna do twarzy i oczu, 400 ml");
        names.add("Dermika Meritum, krem nawilżający z witaminami i bioregulatorem, 50 ml");
        names.add("Dermedic Tolerans Sensitive, krem odżywczy, na noc, 55 g");
        names.add("Vichy, antyperspirant w kulce 48h, kuracja przeciw nadmiernemu poceniu, 50 ml");
        names.add("Bio-Oil, olejek na rozstępy i blizny, 200 ml");
        names.add("Vichy, antyperspirant w kulce do skóry wrażliwej lub po depilacji, 50 ml");
        names.add("Dermedic Antipersp R, antyperspirant, roll-on skóra wrażliwa, 60 g");
        names.add("Dermedic Hydrain 3, serum nawadniające, 30 ml");
        names.add("Biovax A + E, serum wzmacniające włosy, 15 ml");
        names.add("La Roche-Posay Cicaplast Baume B5, kojący balsam regenerujący, 100 ml");
        names.add("Dermedic Emolient Linum, krem do rąk, regenerujący, 100 g");
        names.add("Biovax Silk, jedwab w płynie, 15 ml");
        names.add("La Roche-Posay, Lipikar Gel Lavant, żel myjący, 400 ml");
        names.add("Soraya Kolagen i Elastyna, regenerujący krem półtłusty, na dzień i na noc, 50 ml");
        names.add("Biovax, serum odżywcze do paznokci, 15 ml");
        names.add("Soraya Kolagen i Elastyna, pielęgnacyjny krem nawilżający na dzień i na noc, 50 ml");
        names.add("Tołpa Expert Kids, pasta do zębów dla dzieci 0-6 lat, 50 ml");
        names.add("Dermedic Hydrain 3, żel kremowy do mycia, 200 ml");
        names.add("Soraya Lactissima Ciąża i Połóg, emulsja do higieny intymnej, 300 ml");
        names.add("Soraya Kolagen + Elastyna, przeciwzmarszczkowy krem tłusty na dzień i na noc, 50 ml");
        names.add("Dermedic Normacne Preventi, krem regulująco-oczyszczający na noc, 55 g");
        names.add("Dermedic Normacne Therapy, preparat punktowy, skóra trądzikowa, 15 g");
        names.add("Soraya Kolagen i Elastyna, mleczko do demakijażu, 200 ml");
        names.add("Tołpa Expert Junior, płyn do płukania ust dla dzieci 6-12 lat, czerwone owoce, 250 ml");
        names.add("DOZ PRODUCT Multiwitamina 50+, tabletki powlekane, 60 szt.");
        names.add("Kerabione, dla włosów, paznokci, skóry, kapsułki, 60 szt.");
        names.add("Protego Luteina Complex, kapsułki elastyczne, 30 szt.");
        names.add("Kalcikinon, tabletki, 60 szt.");
        names.add("Calcynovit 1250+D3, tabletki powlekane, 60 szt.");
        names.add("Kinon, tabletki, 30 szt.");
        names.add("VitBaby D, kapsułki twist-off, 30 szt.");
        names.add("Olimp Gold-Vit C1000 Forte, kapsułki, 60 szt.");
        names.add("DOZ PRODUCT Kwas foliowy, tabletki, 90 szt.");
        names.add("Olimp B12 MAX, tabletki, 60 szt.");
        names.add("Doppelherz Vital Tonik, płyn, 750 ml");
        names.add("DOZ PRODUCT Magnez+B6, tabletki powlekane, 60 szt.");
        names.add("Bodymax Plus, tabletki, 60 szt. + kubek GRATIS");
        names.add("WITAMUSKI, tabletki do ssania, lekko musujące o smaku owocowym, 60 szt.");
        names.add("Doppelherz Vital Tonik, płyn, 1000 ml");
        names.add("Witamina C Labor 1000 mg Natural, saszetki, 10 szt.");
        names.add("Gym Food Witamina B3 niacyna, kapsułki, 60 szt.");
        names.add("Revitaben Cardio, płyn, 1000 ml");
        names.add("Bodymax 50+, tabletek, 60 szt. + kubek GRATIS");
        names.add("Zestaw Promocyjny Pharmaton Geriavit, kapsułki, 100 szt. + 30 szt.");
        names.add("Bodymax Plus, tabletek, 200 szt. + kubek GRATIS");
        names.add("DOZ PRODUCT Czosnek bezzapachowy forte, kapsułki, 90 szt.");
        names.add("DOZ PRODUCT Tymianek i podbiał, pastylki do ssania bez cukru, 24 szt.");
        names.add("DOZ PRODUCT Multiwitamina 50+, tabletki powlekane, 60 szt.");
        names.add("ZIELNIK DOZ Szałwia, zioła do zaparzania, 1,2 g, 30 saszetek");
        names.add("ZIELNIK DOZ Dbam o odporność, herbatka ziołowa, 2 g, 10 saszetek");
        names.add("DOZ PRODUCT, Antussimin, syrop, 120 ml");
        names.add("ZIELNIK DOZ, syrop malinowy, 250 ml");
        names.add("Olimp Gold-Vit C1000 Forte, kapsułki, 60 szt.");
        names.add("Miód Manuka MGO 400+, nektarowy, 500 g");
        names.add("Miód Manuka MGO 400+, nektarowy, 250 g");
        names.add("Miód Manuka MGO 250+, nektarowy, 250 g");
        names.add("Miód Manuka MGO 250+, nektarowy, 500 g");
        names.add("Echimunn, tabletki, 30 szt.");
        names.add("Miód Manuka MGO 100+, nektarowy, 250 g");
        names.add("Miód Manuka MGO 100+, nektarowy, 500 g");
        names.add("Miód Manuka MGO 550+, nektarowy, 500 g");
        names.add("Witamina C Labor 1000 mg Natural, saszetki, 10 szt.");
        names.add("Miód Manuka MGO 550+, nektarowy, 250 g");
        names.add("Olimp Gold-Vit C 2000 Shot, płyn w ampułkach, smak cytrynowy, 25 ml, 1 szt.");
        names.add("Echimunn C, proszek w saszetkach, 20 szt.");
        names.add("Rutinoscorbin, tabletki powlekane, 150 szt.");
        names.add("ZIELNIK DOZ Herbatka ziołowa Harmonia, 1,3 g, 20 szt.");
        names.add("ZIELNIK DOZ Dbam o serce, herbatka ziołowa, 2 g, 10 saszetek");
        names.add("ZIELNIK DOZ Dbam o odporność, herbatka ziołowa, 2 g, 10 saszetek");
        names.add("ZIELNIK DOZ Rozgrzewająca herbatka, 2 g, 20 saszetek");
        names.add("ZIELNIK DOZ Herbata PU-ERH, 1,8 g, 20 saszetek");
        names.add("ZIELNIK DOZ Herbata Rooibos Manuka, 1,7 g, 20 saszetek");
        names.add("ZIELNIK DOZ, Yerba Mate o smaku grejpfruta z figą, 1,7 g, 20 saszetek");
        names.add("ZIELNIK DOZ Karczoch, tabletki powlekane, 60 szt.");
        names.add("ZIELNIK DOZ Melisa, tabletki powlekane, 30 szt.");
        names.add("ZIELNIK DOZ Ostropest, tabletki powlekane, 30 szt.");
        names.add("ZIELNIK DOZ Czarny bez z imbirem, cukierki nadziewane, 60 g");
        names.add("ZIELNIK DOZ Dzika Róża, cukierki ziołowe, 60 g");
        names.add("ZIELNIK DOZ Melisa, cukierki ziołowe, 60 g");
        names.add("ZIELNIK DOZ Bratek (fiołek trójbarwny), zioła do zaparzania, 2 g, 30 saszetek");
        names.add("ZIELNIK DOZ Czystek, zioła do zaparzania, 1,5 g, 30 saszetek");
        names.add("ZIELNIK DOZ Szałwia, zioła do zaparzania, 1,2 g, 30 saszetek");
        names.add("ZIELNIK DOZ Morwa biała, zioła do zaparzania, 2 g, 30 saszetek");
        names.add("ZIELNIK DOZ Skrzyp polny, zioła do zaparzania, 1,8 g, 30 saszetek");
        names.add("ZIELNIK DOZ Lipa, zioła do zaparzania, 1,5 g, 30 saszetek");
        names.add("ZIELNIK DOZ Koper włoski, zioła do zaparzania, 2 g, 30 saszetki");
        names.add("ZIELNIK DOZ Dzika Róża, herbatka owocowo-ziołowa, 2 g, 20 szt.");

        return names;
    }
}
