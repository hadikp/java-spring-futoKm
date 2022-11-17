package vizsgaremek.training;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import vizsgaremek.trackpoint.TrackPoint;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainingService {

    private TrainingRepository repository;
    private ModelMapper modelMapper;

    public List<TrainingDto> listAllTraining(Optional<LocalDate> date) {
        List<Training> trainings = repository.findAll();
        return trainings.stream().map(t -> modelMapper.map(t, TrainingDto.class)).collect(Collectors.toList());
    }

    public TrainingDto findById(Long id) {
        Training findTraining = repository.findById(id).orElseThrow(() -> new TrainingNotFoundException(id));
        return modelMapper.map(findTraining, TrainingDto.class);
    }

    public TrainingDto createTraining(TrainingCommand command) {
        List<TrackPoint> trackPoints = command.getTrackpoints();
        Training training = new Training(command.getName(), command.getDescription(), command.getDate());
        training.setTrackpoints(trackPoints);
        double sumM = training.getTrainingAllDistance(); // sum distance in meter
        double sumKm = training.convertKmAndTwoDigits(sumM);
        training.setKm(sumKm);
        repository.save(training);
        return modelMapper.map(training, TrainingDto.class);
    }

    public void deleteTraining(Long id) {
        repository.deleteById(id);
    }

    public double getSumKm(Long id) {
        Training findTraining = repository.findById(id).orElseThrow(() -> new TrainingNotFoundException(id));
        return findTraining.getTrainingAllDistance();
    }

    public double getSumElevation(Long id) {
        Training findTraining = repository.findById(id).orElseThrow(() -> new TrainingNotFoundException(id));
        return findTraining.getTrainingSumElevation();
    }
}
