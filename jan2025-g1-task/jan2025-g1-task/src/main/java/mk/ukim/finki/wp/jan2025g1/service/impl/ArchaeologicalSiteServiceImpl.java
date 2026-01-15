package mk.ukim.finki.wp.jan2025g1.service.impl;

import mk.ukim.finki.wp.jan2025g1.model.ArchaeologicalSite;
import mk.ukim.finki.wp.jan2025g1.model.HistoricalPeriod;
import mk.ukim.finki.wp.jan2025g1.model.SiteLocation;
import mk.ukim.finki.wp.jan2025g1.model.exceptions.InvalidArchaeologicalSiteIdException;
import mk.ukim.finki.wp.jan2025g1.repository.ArchaeologicalSiteRepository;
import mk.ukim.finki.wp.jan2025g1.service.ArchaeologicalSiteService;
import mk.ukim.finki.wp.jan2025g1.service.FieldFilterSpecification;
import mk.ukim.finki.wp.jan2025g1.service.SiteLocationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchaeologicalSiteServiceImpl implements ArchaeologicalSiteService {

    private final ArchaeologicalSiteRepository archaeologicalSiteRepository;
    private final SiteLocationService siteLocationService;

    public ArchaeologicalSiteServiceImpl(ArchaeologicalSiteRepository archaeologicalSiteRepository, SiteLocationService siteLocationService) {
        this.archaeologicalSiteRepository = archaeologicalSiteRepository;
        this.siteLocationService = siteLocationService;
    }

    @Override
    public List<ArchaeologicalSite> listAll() {
        return this.archaeologicalSiteRepository.findAll();
    }

    @Override
    public ArchaeologicalSite findById(Long id) {
        return this.archaeologicalSiteRepository.findById(id).orElseThrow(InvalidArchaeologicalSiteIdException::new);
    }

    @Override
    public ArchaeologicalSite create(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {
        SiteLocation location = this.siteLocationService.findById(locationId);
        ArchaeologicalSite site = new ArchaeologicalSite(
                name,
                areaSize,
                rating,
                period,
                location
        );
        return this.archaeologicalSiteRepository.save(site);
    }

    @Override
    public ArchaeologicalSite update(Long id, String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId) {
        ArchaeologicalSite site = this.findById(id);
        SiteLocation location = this.siteLocationService.findById(locationId);

        site.setName(name);
        site.setAreaSize(areaSize);
        site.setRating(rating);
        site.setPeriod(period);
        site.setLocation(location);

        return this.archaeologicalSiteRepository.save(site);
    }

    @Override
    public ArchaeologicalSite delete(Long id) {
        ArchaeologicalSite site = this.findById(id);
        this.archaeologicalSiteRepository.delete(site);
        return site;
    }

    @Override
    public ArchaeologicalSite close(Long id) {
        ArchaeologicalSite site = this.findById(id);
        site.setClosed(true);
        return this.archaeologicalSiteRepository.save(site);
    }

    @Override
    public Page<ArchaeologicalSite> findPage(String name, Double areaSize, Double rating, HistoricalPeriod period, Long locationId, int pageNum, int pageSize) {
        Specification<ArchaeologicalSite> specification = Specification.allOf(
                FieldFilterSpecification.filterContainsText(ArchaeologicalSite.class, "name", name),
                FieldFilterSpecification.greaterThan(ArchaeologicalSite.class, "areaSize", areaSize),
                FieldFilterSpecification.greaterThan(ArchaeologicalSite.class, "rating", rating),
                FieldFilterSpecification.filterEqualsV(ArchaeologicalSite.class, "period", period),
                FieldFilterSpecification.filterEquals(ArchaeologicalSite.class, "location.id", locationId)
        );

        return this.archaeologicalSiteRepository.findAll(specification, PageRequest.of(
                pageNum,
                pageSize
        ));
    }
}
