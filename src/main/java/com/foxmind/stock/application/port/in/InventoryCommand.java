package com.foxmind.stock.application.port.in;

import com.foxmind.stock.application.exception.InventoryInternalErrorException;
import com.foxmind.stock.domain.dto.request.InventoryRequest;
import com.foxmind.stock.domain.dto.response.InventoryResponse;

public interface InventoryCommand {
    InventoryResponse create(InventoryRequest request) throws InventoryInternalErrorException;
}
