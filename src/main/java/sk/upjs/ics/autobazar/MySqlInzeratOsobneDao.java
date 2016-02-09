package sk.upjs.ics.autobazar;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;

public class MySqlInzeratOsobneDao implements InzeratOsobneDao{
    private JdbcTemplate jdbcTemplate;
    
    public MySqlInzeratOsobneDao() {
        MysqlDataSource dataSource = InzeratFactory.INSTANCE.dataSource();        
        jdbcTemplate = InzeratFactory.INSTANCE.jdbcTemplate();
    }
    
    @Override
    public void pridat(InzeratOsobne inzerat) {
        String sql = "INSERT INTO inzerat VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,inzerat.getIdP(),null,inzerat.getZnacka(),inzerat.getModel(),inzerat.getRocnik(),inzerat.getKm(),inzerat.getObjem(),inzerat.getPrevodovka(),inzerat.getVykon(),inzerat.getDatumPridania(), inzerat.getCena(),inzerat.getObrazok(), inzerat.isKlimatizacia(), inzerat.isTazneZariadenie(), inzerat.isVyhrievaneSedadla());
    }

    @Override
    public List<InzeratOsobne> dajVsetky() {
        String sql = "SELECT * FROM inzerat";
        BeanPropertyRowMapper<InzeratOsobne> mapper = BeanPropertyRowMapper.newInstance(InzeratOsobne.class);
        return jdbcTemplate.query(sql, mapper); /*dam dopyt a mapper a vypuje zoznam uloh ktore mozme pouzit*/
    }    

    @Override
    public void odstranit(InzeratOsobne inzerat) {
        String sql = "delete from inzerat where id = ?";
        jdbcTemplate.update(sql, inzerat.getId());
    }

    @Override
    public List<InzeratOsobne> vyhladaj(String znacka, String model, String odRocnik, String doRocnik) {
        String sql = "select * from inzerat where znacka = ? and model = ? and rocnik >= ? and rocnik <= ?";
        BeanPropertyRowMapper<InzeratOsobne> mapper = BeanPropertyRowMapper.newInstance(InzeratOsobne.class);
        return jdbcTemplate.query(sql, mapper,znacka,model,odRocnik,doRocnik); 
        
        /*String sql = "SELECT * FROM inzerat";
        BeanPropertyRowMapper<InzeratOsobne> mapper = BeanPropertyRowMapper.newInstance(InzeratOsobne.class);
        List<InzeratOsobne> pomocna = jdbcTemplate.query(sql, mapper);
        List<InzeratOsobne> pomocna2 = new LinkedList<InzeratOsobne>();
        for (InzeratOsobne a1 : pomocna) {
            if (a1.getZnacka().equals(znacka)) {
                if (a1.getModel().equals(model)) {
                    int a = a1.getRocnik().compareTo(odRocnik);
                    int b = a1.getRocnik().compareTo(doRocnik);
                    if (a >= 0 && b <= 0) {
                        System.out.println(" ");
                        pomocna2.add(a1);
                    }
                }
            }
        }
        return pomocna2;*/
    }

    @Override
    public List<InzeratOsobne> dajPodlaPouzivatela(Long idP) {
        String sql = "SELECT * FROM inzerat where idP = ?";
        BeanPropertyRowMapper<InzeratOsobne> mapper = BeanPropertyRowMapper.newInstance(InzeratOsobne.class);
        return jdbcTemplate.query(sql, mapper,idP);
    }

    @Override
    public List<InzeratOsobne> vyhladajViac(String znacka, String model, String odRocnik, String doRocnik, int odKm, int doKm, long odCena, long doCena, boolean klimatizacia, boolean tazneZariadenie, boolean vyhrievaneSedadla) {
        String sql = "select * from inzerat where znacka = ? and model = ? and rocnik >= ? and rocnik <= ? and klimatizacia = ? and tazneZariadenie = ? and vyhrievaneSedadla = ? and km >= ? and km <= ? and cena >= ? and cena <= ?";
        BeanPropertyRowMapper<InzeratOsobne> mapper = BeanPropertyRowMapper.newInstance(InzeratOsobne.class);
        return jdbcTemplate.query(sql, mapper, znacka, model, odRocnik, doRocnik, klimatizacia, tazneZariadenie, vyhrievaneSedadla, odKm , doKm, odCena, doCena);
    }
}
