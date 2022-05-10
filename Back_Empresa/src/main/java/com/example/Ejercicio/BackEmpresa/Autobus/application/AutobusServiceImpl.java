package com.example.Ejercicio.BackEmpresa.Autobus.application;

import com.example.Ejercicio.BackEmpresa.Autobus.domain.Autobus;
import com.example.Ejercicio.BackEmpresa.Autobus.domain.AutobusID;
import com.example.Ejercicio.BackEmpresa.Autobus.infrastructure.repository.AutobusRepository;
import com.example.Ejercicio.BackEmpresa.Reserva.domain.Reserva;
import com.example.Ejercicio.BackEmpresa.Reserva.infrastructure.controller.DTO.ReservaInputDTO;
import com.example.Ejercicio.BackEmpresa.shared.exceptions.NoPlazasException;
import com.example.Ejercicio.BackEmpresa.shared.exceptions.NotFoundException;
import com.example.Ejercicio.BackEmpresa.shared.exceptions.UnprocesableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AutobusServiceImpl implements AutobusService{

    @Autowired
    AutobusRepository autobusRepo;


    @Override
    public Autobus checkAutobus(ReservaInputDTO reservaInputDTO) {

       Optional <Autobus> autobus= autobusRepo.findById(new AutobusID(reservaInputDTO.getCiudad(),reservaInputDTO.getFecha(), reservaInputDTO.getHora()));
       if (autobus.isPresent()){
           int plazas= autobus.get().getPlazasdisponibles();
           if (plazas==0){
               throw new NoPlazasException("No quedan plazas en el autobús");
           }
           autobus.get().setPlazasdisponibles(plazas-1);
           autobusRepo.save(autobus.get());
           return autobus.get();
       } else {
           Autobus autobus1= new Autobus();
           AutobusID autobusID= new AutobusID(reservaInputDTO.getCiudad(),reservaInputDTO.getFecha(),reservaInputDTO.getHora());
           autobus1.setId(autobusID);
           autobus1.setTotalPlazas(40);
           autobus1.setPlazasdisponibles(39);
           autobusRepo.save(autobus1);
           return autobus1;
       }
    }

    @Override
    public Autobus findAutobus(String ciudad, Date fecha, Float hora)  {


       Autobus autobus = autobusRepo.findById(new AutobusID(ciudad,fecha,hora)).orElseThrow(()-> new NotFoundException("No se ha encontrado el autobús"));
       return autobus;

    }

    @Override
    public String findPlazaslibres(String ciudad, Date fecha, Float hora) {
        Autobus autobus= this.findAutobus(ciudad,fecha,hora);
        return "Plazas libres: " + autobus.getPlazasdisponibles();
    }

    @Override
    public void updateAutobus(Autobus autobus) {
        int capacidad=40;
        List<Reserva> list=autobus.getReservas();
        int numReservas=list.size();
        autobus.setPlazasdisponibles(capacidad-numReservas);
        autobusRepo.save(autobus);
    }


}
