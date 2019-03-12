package com.gildedrose.web.adapter.dto;

public interface  DtoAdapter<M, D> {
    /**
     * Transform an {@link M} into a DTO of type {@link D}
     * @param itemModel
     * @return
     */
    public D toDto(M itemModel);

    /**
     * Transform an {@link D} into a Model object of type {@link M}
     * @param itemDto
     * @return
     */
    public M toModel(D itemDto);

}
