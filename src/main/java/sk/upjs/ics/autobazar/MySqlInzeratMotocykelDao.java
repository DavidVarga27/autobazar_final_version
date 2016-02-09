package sk.upjs.ics.autobazar;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySqlInzeratMotocykelDao implements InzeratMotocykelDao{
    private JdbcTemplate jdbcTemplate;
    
    public MySqlInzeratMotocykelDao() {
        MysqlDataSource dataSource = InzeratFactory.INSTANCE.dataSource();        
        jdbcTemplate = InzeratFactory.INSTANCE.jdbcTemplate();
    }

    @Override
    public void pridat(InzeratMotocykel inzerat) {
        String sql = "INSERT INTO inzeratMotocykel VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,inzerat.getIdP(),null,inzerat.getZnacka(),inzerat.getModel(),inzerat.getRocnik(),inzerat.getKm(),inzerat.getObjem(),inzerat.getPrevodovka(),inzerat.getVykon(),inzerat.getDatumPridania(), inzerat.getCena(),inzerat.getObrazok());
    }

    @Override
    public List<InzeratMotocykel> dajVsetky() {
        String sql = "SELECT * FROM inzeratMotocykel";
        BeanPropertyRowMapper<InzeratMotocykel> mapper = BeanPropertyRowMapper.newInstance(InzeratMotocykel.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<InzeratMotocykel> vyhladaj(String znacka, String model, String odRocnik, String doRocnik) {
        String sql = "SELECT * FROM inzeratMotocykel";
        BeanPropertyRowMapper<InzeratMotocykel> mapper = BeanPropertyRowMapper.newInstance(InzeratMotocykel.class);
        List<InzeratMotocykel> pomocna = jdbcTemplate.query(sql, mapper);
        List<InzeratMotocykel> pomocna2 = new LinkedList<InzeratMotocykel>();
        for (InzeratMotocykel a1 : pomocna) {
            if (a1.getZnacka().equals(znacka)) {
                if (a1.getModel().equals(model)) {
                    int a = a1.getRocnik().compareTo(odRocnik);
                    int b = a1.getRocnik().compareTo(doRocnik);
                    if (a >= 0 && b <= 0) {
                        //System.out.println(" ");
                        pomocna2.add(a1);
                    }
                }
            }
        }
        return pomocna2;
    }

    @Override
    public void odstranit(InzeratMotocykel inzerat) {
        String sql = "delete from inzeratMotocykel where id = ?";
        jdbcTemplate.update(sql, inzerat.getId());
    }

    @Override
    public List<InzeratMotocykel> dajPodlaPouzivatela(Long idP) {
        String sql = "SELECT * FROM inzeratMotocykel where idP = ?";
        BeanPropertyRowMapper<InzeratMotocykel> mapper = BeanPropertyRowMapper.newInstance(InzeratMotocykel.class);
        return jdbcTemplate.query(sql, mapper,idP);
    }
    
}
