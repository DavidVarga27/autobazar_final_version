/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.upjs.ics.autobazar;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.LinkedList;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySqlInzeratNakladneDao implements InzeratNakladneDao {
    private JdbcTemplate jdbcTemplate;
    
    public MySqlInzeratNakladneDao() {
        MysqlDataSource dataSource = InzeratFactory.INSTANCE.dataSource();        
        jdbcTemplate = InzeratFactory.INSTANCE.jdbcTemplate();
    }

    @Override
    public void pridat(InzeratNakladne inzerat) {
        String sql = "INSERT INTO inzeratNakladne VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,inzerat.getIdP(),null,inzerat.getZnacka(),inzerat.getModel(),inzerat.getRocnik(),inzerat.getKm(),inzerat.getObjem(),inzerat.getPrevodovka(),inzerat.getVykon(),inzerat.getDatumPridania(), inzerat.getCena(), inzerat.getObrazok(), inzerat.isKlimatizacia(), inzerat.isTazneZariadenie(), inzerat.isVyhrievaneSedadla());
    }

    @Override
    public List<InzeratNakladne> dajVsetky() {
        String sql = "SELECT * FROM inzeratNakladne";
        BeanPropertyRowMapper<InzeratNakladne> mapper = BeanPropertyRowMapper.newInstance(InzeratNakladne.class);
        return jdbcTemplate.query(sql, mapper);
    }

    @Override
    public List<InzeratNakladne> vyhladaj(String znacka, String model, String odRocnik, String doRocnik) {
        String sql = "SELECT * FROM inzeratNakladne";
        BeanPropertyRowMapper<InzeratNakladne> mapper = BeanPropertyRowMapper.newInstance(InzeratNakladne.class);
        List<InzeratNakladne> pomocna = jdbcTemplate.query(sql, mapper);
        List<InzeratNakladne> pomocna2 = new LinkedList<InzeratNakladne>();
        for (InzeratNakladne a1 : pomocna) {
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
    public void odstranit(InzeratNakladne inzerat) {
        String sql = "delete from inzeratNakladne where id = ?";
        jdbcTemplate.update(sql, inzerat.getId());
    }

    @Override
    public List<InzeratNakladne> dajPodlaPouzivatela(Long idP) {
        String sql = "SELECT * FROM inzeratNakladne where idP = ?";
        BeanPropertyRowMapper<InzeratNakladne> mapper = BeanPropertyRowMapper.newInstance(InzeratNakladne.class);
        return jdbcTemplate.query(sql, mapper,idP);
    }

    @Override
    public List<InzeratNakladne> vyhladajViac(String znacka, String model, String odRocnik, String doRocnik, int odKm, int doKm, long odCena, long doCena,boolean klimatizacia, boolean tazneZariadenie, boolean vyhrievaneSedadla) {
        String sql = "select * from inzeratNakladne where znacka = ? and model = ? and rocnik >= ? and rocnik <= ? and klimatizacia = ? and tazneZariadenie = ? and vyhrievaneSedadla = ? and km >= ? and km <= ? and cena >= ? and cena <= ?";
        BeanPropertyRowMapper<InzeratNakladne> mapper = BeanPropertyRowMapper.newInstance(InzeratNakladne.class);
        return jdbcTemplate.query(sql, mapper, znacka, model, odRocnik, doRocnik, klimatizacia, tazneZariadenie, vyhrievaneSedadla, odKm, doKm, odCena, doCena);
    }
    
}
