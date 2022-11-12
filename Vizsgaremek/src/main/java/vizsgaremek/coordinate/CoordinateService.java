package vizsgaremek.coordinate;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CoordinateService {

    private CoordinateRepository repository;
    private ModelMapper modelMapper;

    public List<CoordinateDto> listAllCoordinate() {
        List<Coordinate> coordinates = repository.findAll();
        return coordinates.stream().map(t -> modelMapper.map(t, CoordinateDto.class)).collect(Collectors.toList());
    }

    public CoordinateDto findById(Long id) {
        Coordinate findCoordinate = repository.findById(id).orElseThrow(() -> new CoordinateNotFoundException(id));
        return modelMapper.map(findCoordinate, CoordinateDto.class);
    }

    public CoordinateDto createCoordinate(CoordinateCommand command) {
        Coordinate coordinate = new Coordinate(command.getName(), command.getLatitude(), command.getLongitude());
        repository.save(coordinate);
        return modelMapper.map(coordinate, CoordinateDto.class);
    }

    @Transactional
    public CoordinateDto updateCoordinateLatitude(Long id, UpdateCoordinateLatitude command) {
        Coordinate findCoordinate = repository.findById(id).orElseThrow(() -> new CoordinateNotFoundException(id));
        findCoordinate.setLatitude(command.getLatitude());
        return modelMapper.map(findCoordinate, CoordinateDto.class);
    }

    @Transactional
    public CoordinateDto updateCoordinateLongitude(Long id, UpdateCoordinateLongitude command) {
        Coordinate findCoordinate = repository.findById(id).orElseThrow(() -> new CoordinateNotFoundException(id));
        findCoordinate.setLongitude(command.getLongitude());
        return modelMapper.map(findCoordinate, CoordinateDto.class);
    }

    public void deleteCoordinate(Long id) {
        repository.deleteById(id);
    }
}
