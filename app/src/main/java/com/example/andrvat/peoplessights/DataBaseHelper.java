package com.example.andrvat.peoplessights;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;


public class DataBaseHelper extends SQLiteOpenHelper{

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHelper(Context context){
        super(context, "test_db_info_four", null, 1);
    }

    //Выполняется при создании базы данных, нужно написать код для создания таблиц
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery1 = "CREATE TABLE actions (\n" +
                "    _id       INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                      NOT NULL,\n" +
                "    title TEXT,\n" +
                "    latitude  DOUBLE,\n" +
                "    longitude DOUBLE,\n" +
                "    colorMarker TEXT,\n" +
                "    tag   INTEGER, \n" +
                "    descriptionOne TEXT,\n" +
                "    descriptionTwo TEXT,\n" +
                "    pictureOne TEXT,\n" +
                "    pictureTwo TEXT \n" +
                ")";
        sqLiteDatabase.execSQL(sqlQuery1); //Этот метод позволяет выполнить любой SQL-запрос



        String addPit = "INSERT INTO actions (_id, title, Latitude, Longitude, colorMarker, Tag, descriptionOne, descriptionTwo, pictureOne, pictureTwo) VALUES (1, 'Бородинское сражение', 55.52233888063213, 35.81689260028917, 'Blue', 1, '      26 августа 1812 года в сражении под деревней Бородино в 125 километрах западнее Москвы сошлись значительные силы французской и русской армий. Регулярные войска под командованием Наполеона насчитывали около 137 тыс. человек, армия Михаила Кутузова с примкнувшими к ней казаками и ополчением достигала 120 тыс. Пересеченная местность позволяла незаметно перемещать резервы, а на возвышениях установить артиллерийские батареи. 24 августа Наполеон подошел к Шевардинскому редуту, стоявшему близ одноименной деревни, в трех верстах перед Бородинским полем.', '      Бородинская битва началась через день после боя у Шевардинского редута и стала самым масштабным сражением в войне 1812 года. Потери с обеих сторон были колоссальные: французы потеряли 28 тысяч человек, русские — 46,5 тысяч. Хотя Кутузов после битвы отдал приказ об отступлении к Москве, в донесении Александру I победителем в сражении он называл русскую армию. Так считают и многие российские историки. Иначе видят бой при Бородине французские ученые. По их мнению, «в битве у Москвы-реки» победили наполеоновские войска. Сам Наполеон, осмысливая результаты битвы, сказал: «Французы в ней показали себя достойными одержать победу, а русские стяжали право быть непобедимыми».', 'bor1.jpg', 'bor2.jpg');";
        sqLiteDatabase.execSQL(addPit);
        addPit = "INSERT INTO actions (_id, title, Latitude, Longitude, colorMarker, Tag, descriptionOne, descriptionTwo, pictureOne, pictureTwo) VALUES (2, 'Ледовое побоище', 58.7432452887556, 27.501958823433725, 'Yellow', 2, '      В 1242 году немецкие рыцари из Ливонского ордена захватили Псков и подступили к Новгороду. Новгородцы, за год до того поссорившиеся с князем Александром, обратились к нему за помощью и вновь передали ему власть. Князь собрал войско, изгнал врагов из новгородской и псковской земли и вышел к Чудскому озеру.', '      На льду озера в 1242 году в сражении, известном как Ледовое побоище, Александр Ярославич уничтожил войско немецких рыцарей. Русские стрелки, несмотря на натиск немцев, прорывавших полки в центре, мужественно противостояли нападавшим. Эта смелость помогла русским окружить рыцарей с флангов и победить. Преследуя уцелевших целых семь верст, Александр показал твердость русского войска. Победа в битве привела к подписанию мирного соглашения между Новгородом и Ливонским орденом».', 'ice1.jpg', 'ice2.jpg');";
        sqLiteDatabase.execSQL(addPit);
        addPit = "INSERT INTO actions (_id, title, Latitude, Longitude, colorMarker, Tag, descriptionOne, descriptionTwo, pictureOne, pictureTwo) VALUES (3, 'Брусиловский прорыв', 49.691187, 25.350061, 'Blue', 3, '      Одной из важнейших операций на Восточном фронте 1916 года стало наступление на Юго-Западном фронте, призванное не только переломить ход военных действий на Восточном фронте, но и прикрыть наступление союзников на Сомме. Результатом стал Брусиловский прорыв, который существенно подорвал военную мощь австро-венгерской армии и подтолкнул Румынию к вступлению в войну на стороне Антанты.', '      Наступательная операция Юго-Западного фронта под командованием генерала Алексея Брусилова, проведенная с мая по сентябрь 1916 года, стала, по мнению военного историка Антона Керсновского, «победой, какой в мировую войну мы ещё не одерживали». Впечатляет и количество сил, которые были задействованы с обеих сторон – 1 732 000 русских солдат и 1 061 000 солдат австро-венгерской и германской армий.', 'bru1.jpg', 'brunomars.jpg');";
        sqLiteDatabase.execSQL(addPit);
        addPit = "INSERT INTO actions (_id, title, Latitude, Longitude, colorMarker, Tag, descriptionOne, descriptionTwo, pictureOne, pictureTwo) VALUES (4, 'Битва на Курской дуге', 51.58212, 35.508284, 'Red', 4, '      Курская битва — одна из величайших в истории Великой Отечественной войны, ознаменовавших коренной перелом в боевых действиях. После нее стратегическая инициатива полностью перешла в руки советского командования. Развивая успех, достигнутый под Сталинградом, советские войска развернули широкомасштабное наступление на фронте от Воронежа до Черного моря. Одновременно в январе 1943 года был деблокирован осажденный Ленинград. Лишь к весне 1943-го вермахту удалось остановить советское наступление на Украине. Хотя части Красной армии заняли Харьков и Курск, а передовые части Юго-Западного фронта уже сражались в предместьях Запорожья, немецкие войска перешли в контрнаступление и вновь заняли Харьков. В результате линия фронта на южном фланге противостояния приобрела характерную форму, впоследствии получившую название Курской дуги.', '      Именно тут германское командование решило нанести решительное поражение советским войскам. Предполагалось ударами по основанию дуги срезать ее, окружив сразу два советских фронта. Достичь успеха германское командование планировало в том числе и за счет широкого применения новейших типов военной техники. Советское командование знало о планах неприятеля и сознательно решило уступить стратегическую инициативу врагу. Замысел состоял в том, чтобы на заранее подготовленных позициях измотать ударные дивизии вермахта, а затем перейти в контрнаступление. И надо признать: этот план увенчался успехом. В районе станции Прохоровка произошло одно из крупнейших танковых сражений мира, в котором одновременно приняло участие свыше 800 танков. Хотя советские войска в этом сражении также понесли тяжелые потери, наступательный потенциал немцами был утрачен.', 'kur2.jpeg', 'kur3.jpg');";
        sqLiteDatabase.execSQL(addPit);
        addPit = "INSERT INTO actions (_id, title, Latitude, Longitude, colorMarker, Tag, descriptionOne, descriptionTwo, pictureOne, pictureTwo) VALUES (5, 'Стояние на Угре', 54.638549, 35.956314, 'Yellow', 5, '      Это событие знаменует собой окончание влияния Орды на политику русских князей. В 1480 году, после того как Иван III разорвал ханский ярлык, хан Ахмат, заключив союз с литовским князем Казимиром, двинулся на Русь. Стремясь на соединение с литовским войском, он 8 октября подошел к реке Угре, притоку Оки. Здесь его встретила русская рать.', '      Попытка Ахмата форсировать Угру была отражена в четырехдневном сражении. Тогда хан стал ожидать литовцев. Иван III, чтобы выиграть время, начал с ним переговоры. В это время крымский хан Менгли Гирей, союзник Москвы, напал на земли Великого княжества Литовского, что не позволило Казимиру оказать помощь Ахмату. 20 октября в подкрепление Ивану III пришли полки его братьев, Бориса и Андрея Большого. Узнав об этом, Ахмат 11 ноября повернул свое войско назад в степи. Вскоре Ахмат был убит в Орде. Так Русь окончательно разорвала ордынское иго и получила независимость.', 'river1.jpg', 'river2.jpg');";
        sqLiteDatabase.execSQL(addPit);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }




    public ArrayList<String> getCoordinations(){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT latitude, longitude FROM actions ";
        Cursor cursor = readableDatabase.rawQuery(sql,null);
        ArrayList<String> hashMap = new ArrayList<>();
        while(cursor.moveToNext()) {
            double lat = cursor.getDouble(0);
            double lng = cursor.getDouble(1);
            String s = Double.toString(lat) + ";" + Double.toString(lng);
            hashMap.add(s);
        }
        return hashMap;
    }
    public ArrayList<String> getTitles(){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT title FROM actions ";
        Cursor cursor = readableDatabase.rawQuery(sql,null);
        ArrayList<String> hashMap = new ArrayList<>();
        while(cursor.moveToNext()) {
            hashMap.add(cursor.getString(0));
        }
        return hashMap;
    }
    public ArrayList<String> getColor(){
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        String sql = "SELECT colorMarker FROM actions ";
        Cursor cursor = readableDatabase.rawQuery(sql,null);
        ArrayList<String> hashMap = new ArrayList<>();
        while(cursor.moveToNext()) {
            hashMap.add(cursor.getString(0));
        }
        return hashMap;
    }
}