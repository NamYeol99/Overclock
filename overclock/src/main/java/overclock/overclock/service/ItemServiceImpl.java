package overclock.overclock.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import overclock.overclock.dto.*;
import overclock.overclock.entity.Item;
import overclock.overclock.repository.ItemRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ItemServiceImpl implements ItemService {

    @Autowired
    private final ItemRepository itemRepository; //final

    @Transactional
    @Override
    public Long itemPosting(ItemDTO itemDTO) {
        log.info("dto : {}", itemDTO);
        Item item = dtoToEntity(itemDTO);
        log.info(item);
        itemRepository.save(item);
        return item.getId();
    }

    @Override
    public List<Integer> partsItemList(PostsDTO postsDTO) {
        log.info("PostsDTO result : {}", postsDTO);
        String type = postsDTO.getPartsType();
        log.info("type : {}", type);

        List<Integer> result = itemRepository.getPriceByPartstype(type);
        log.info("result : {}", result);
        return result;
    }
}
