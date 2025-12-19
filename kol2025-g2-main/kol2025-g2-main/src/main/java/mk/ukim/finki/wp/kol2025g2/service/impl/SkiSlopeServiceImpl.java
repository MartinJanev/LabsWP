package mk.ukim.finki.wp.kol2025g2.service.impl;

import mk.ukim.finki.wp.kol2025g2.model.SkiResort;
import mk.ukim.finki.wp.kol2025g2.model.SkiSlope;
import mk.ukim.finki.wp.kol2025g2.model.SlopeDifficulty;
import mk.ukim.finki.wp.kol2025g2.model.exceptions.InvalidSkiSlopeIdException;
import mk.ukim.finki.wp.kol2025g2.repository.SkiResortRepository;
import mk.ukim.finki.wp.kol2025g2.repository.SkiSlopeRepository;
import mk.ukim.finki.wp.kol2025g2.service.SkiResortService;
import mk.ukim.finki.wp.kol2025g2.service.SkiSlopeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkiSlopeServiceImpl implements SkiSlopeService {

    private final SkiSlopeRepository skiSlopeRepository;
    private final SkiResortService skiResortRepository;

    public SkiSlopeServiceImpl(SkiSlopeRepository skiSlopeRepository, SkiResortService skiResortRepository) {
        this.skiSlopeRepository = skiSlopeRepository;
        this.skiResortRepository = skiResortRepository;
    }

    @Override
    public List<SkiSlope> listAll() {
        return skiSlopeRepository.findAll();
    }

    @Override
    public SkiSlope findById(Long id) {
        return this.skiSlopeRepository.findById(id).orElseThrow(() -> new InvalidSkiSlopeIdException());
    }

    @Override
    public SkiSlope create(String name, Integer length, SlopeDifficulty difficulty, Long skiResort) {
        SkiResort resort = this.skiResortRepository.findById(skiResort);
        SkiSlope slope = new SkiSlope(
                name,
                length,
                difficulty,
                resort
        );

        return this.skiSlopeRepository.save(slope);
    }

    @Override
    public SkiSlope update(Long id, String name, Integer length, SlopeDifficulty difficulty, Long skiResort) {
        SkiSlope slope = this.findById(id);
        SkiResort resort = this.skiResortRepository.findById(skiResort);

        slope.setName(name);
        slope.setLength(length);
        slope.setDifficulty(difficulty);
        slope.setSkiResort(resort);

        return this.skiSlopeRepository.save(slope);
    }

    @Override
    public SkiSlope delete(Long id) {
        SkiSlope slope = this.findById(id);
        this.skiSlopeRepository.delete(slope);
        return slope;
    }

    @Override
    public SkiSlope close(Long id) {
        SkiSlope slope = this.findById(id);
        slope.setClosed(true);
        return this.skiSlopeRepository.save(slope);
    }

    @Override
    public Page<SkiSlope> findPage(String name, Integer length, SlopeDifficulty difficulty, Long skiResort, int pageNum, int pageSize) {
        Specification<SkiSlope> spec = Specification.allOf(
                FieldFilterSpecification.filterContainsText(SkiSlope.class, "name", name),
                FieldFilterSpecification.filterEqualsV(SkiSlope.class, "difficulty", difficulty),
                FieldFilterSpecification.greaterThan(SkiSlope.class, "length", length),
                FieldFilterSpecification.filterEquals(SkiSlope.class, "skiResort.id", skiResort)
        );

        return this.skiSlopeRepository.findAll(spec, PageRequest.of(pageNum, pageSize));
    }
}
